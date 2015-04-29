/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.Helpers;

import MQApi.Connection.MQConnection;
import MQApi.Models.Query.ConnectionDetailModel;
import UI.MainWindow;
import UI.Models.TreeNodeObject;
import com.ibm.mq.MQException;
import com.ibm.mq.MQQueueManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

/**
 *
 * @author jzhou
 */
public class TreeHelper {
    
    public static void InitTreeView(JTree TreeView){
        TreeView.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        DefaultTreeModel model = (DefaultTreeModel)TreeView.getModel();
        DefaultMutableTreeNode root = (DefaultMutableTreeNode)model.getRoot();
        root.removeAllChildren();
        TreeNodeObject rootObject = new TreeNodeObject("MQ Admin Explorer", TreeNodeObject.TreeNodeObjectType.Root);        
        TreeNodeObject queueManagerFolderObject = new TreeNodeObject("Queue Managers", TreeNodeObject.TreeNodeObjectType.QueueManagerFolder);
        root.setUserObject(rootObject);
        DefaultMutableTreeNode queueManagerParentNode  = new DefaultMutableTreeNode(queueManagerFolderObject);
        root.add(queueManagerParentNode);
        model.reload(root);          
    }
        
    public static void AddQueueManagerNodeToTreeView(JTree TreeView, ConnectionDetailModel connectionDetail){
        TreeNodeObject nodeObject = new TreeNodeObject(connectionDetail.QueueManager, TreeNodeObject.TreeNodeObjectType.QueueManager);
        nodeObject.ConnectionDetail = connectionDetail;
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(nodeObject);
        DefaultTreeModel model = (DefaultTreeModel)TreeView.getModel();
        DefaultMutableTreeNode root = (DefaultMutableTreeNode)model.getRoot();
        DefaultMutableTreeNode queueManagerFolderNode = (DefaultMutableTreeNode)root.getFirstChild();
        queueManagerFolderNode.add(node);
        model.reload(root);
        TreeView.setSelectionPath(new TreePath(node.getPath()));
    }
    
    public static ConnectionDetailModel DeleteQueueManagerNodeFromTreeView(JTree TreeView){
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) TreeView.getLastSelectedPathComponent();
        TreeNodeObject nodeObject = (TreeNodeObject) node.getUserObject();

        DefaultMutableTreeNode parent = (DefaultMutableTreeNode)node.getParent();
        if(nodeObject.IsConnected()){
            try {
                nodeObject.QueueManager.disconnect();
            } catch (MQException ex) {
                Logger.getLogger(TreeHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        parent.remove(node);
        RepoadTree(TreeView);
        return nodeObject.ConnectionDetail;
    }
    
    public static void AddConnectedQueueManagerNode(JTree TreeView) throws MQException{
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) TreeView.getLastSelectedPathComponent();
        TreeNodeObject nodeObject = (TreeNodeObject) selectedNode.getUserObject();
        ConnectionDetailModel connectionDetail = nodeObject.ConnectionDetail;
        MQQueueManager queueManager = MQConnection.GetMQQueueManager(connectionDetail);
        if(queueManager != null){
            nodeObject.QueueManager = queueManager;
            TreeNodeObject queuesObject = new TreeNodeObject("Queues",TreeNodeObject.TreeNodeObjectType.Queues);
            TreeNodeObject channelObject = new TreeNodeObject("Channels",TreeNodeObject.TreeNodeObjectType.Channel);
            DefaultMutableTreeNode queueNodes = new DefaultMutableTreeNode(queuesObject);
            DefaultMutableTreeNode channelNodes = new DefaultMutableTreeNode(channelObject);
            selectedNode.add(queueNodes);
            selectedNode.add(channelNodes);           
            RepoadTree(TreeView);
            TreeView.setSelectionPath(new TreePath(selectedNode.getPath()));
        }
    }
    
    public static void DisconnectQueueManager(JTree TreeView) throws MQException{
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) TreeView.getLastSelectedPathComponent();
        TreeNodeObject nodeObject = (TreeNodeObject) selectedNode.getUserObject();
        selectedNode.removeAllChildren();
        RepoadTree(TreeView);
        TreeView.setSelectionPath(new TreePath(selectedNode.getPath()));   
        if(nodeObject.IsConnected()){
            nodeObject.QueueManager.disconnect();
        }
    
    }
    
    public static ConnectionDetailModel GetCurrentSelectedConnectionDetail(JTree TreeView){
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) TreeView.getLastSelectedPathComponent();
        if(node != null){
            TreeNodeObject nodeObject = (TreeNodeObject) node.getUserObject();  
            return nodeObject.ConnectionDetail;
        }
        return null;
    }
    
    public static MQQueueManager GetCurrentSelectedQueueManager(JTree TreeView){
        if(TreeView.getLastSelectedPathComponent() != null){
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) TreeView.getLastSelectedPathComponent();
            DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) node.getParent();
            if(parentNode != null){
                TreeNodeObject nodeObject = (TreeNodeObject) parentNode.getUserObject();  
                return nodeObject.QueueManager;
            }
        }
        return null;        
    }
    
    public static boolean CurrentSelectedNodeHasQueueManager(JTree TreeView){
        return GetCurrentSelectedQueueManager(TreeView) != null;
    }
    
    public static void RefreshTreeView(JTree TreeView, ConnectionDetailModel connectionDetail){
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) TreeView.getLastSelectedPathComponent();
        TreeNodeObject nodeObject = (TreeNodeObject) node.getUserObject();
        nodeObject.Name = connectionDetail.QueueManager;
        nodeObject.ConnectionDetail = connectionDetail;
        RepoadTree(TreeView);
        TreeView.setSelectionPath(new TreePath(node.getPath()));
    }
    
    private static void RepoadTree(JTree TreeView){
        DefaultTreeModel model = (DefaultTreeModel)TreeView.getModel();
        DefaultMutableTreeNode root = (DefaultMutableTreeNode)model.getRoot();
        model.reload(root);
    }

}
