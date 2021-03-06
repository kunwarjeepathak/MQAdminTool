/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.Dialogs;

import MQApi.Enums.LogType;
import MQApi.Enums.QueueType;
import MQApi.Logs.LogWriter;
import MQApi.Models.MQMessageIdModel;
import Tasks.BackupRestoreMessageTask;
import UI.Helpers.IconManager;
import UI.Models.TableListObject;
import com.ibm.mq.MQException;
import com.ibm.mq.MQQueueManager;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import static javax.swing.JFileChooser.OPEN_DIALOG;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author jzhou
 */
public class BackupRestoreMessageDialog extends DialogBase{
    public static int Usage_Backup = 0;
    public static int Usage_Restore = 1;
    public static int Usage_SaveMsgContent = 2;

    public boolean IsBackup;
    String selectedFilePath;
    ArrayList<MQMessageIdModel> ids;
    int usageOption;
    
    public BackupRestoreMessageDialog(java.awt.Frame parent, boolean modal, MQQueueManager queueManager, TableListObject selectedObject, ArrayList<MQMessageIdModel> ids) {
        super(parent, modal,queueManager,selectedObject);
        initComponents();
        initCustomProperties();
        this.ids = ids;
    }
    
    public void SetUsage(int option){
        this.usageOption = option;
        String title = "";
        if(option == Usage_Backup || option == Usage_SaveMsgContent ){
            this.IsBackup = true;
            if(option == Usage_Backup){
                title = "Backup messages";
            }
            else{
                title = "Save messages content to file";
            }
        }
        else if(option == Usage_Restore){
            this.IsBackup = false;
            title = "Restore message";
        }
        
        this.setTitle(title);
        this.setIconImage(IsBackup == true ? iconManager.BackupMessageIcon().getImage() : iconManager.RestoreMessageIcon().getImage());
        this.OkButton.setText(IsBackup ? "Backup" : "Restore");
        this.CompressCheckBox.setEnabled(option == Usage_Backup);
        this.RemoveDLHCheckBox.setEnabled(option == Usage_Backup);
        this.OptionPanel.setEnabled(option == Usage_Backup);
        this.FilterLabel.setEnabled(option == Usage_Backup && ids == null);
        this.FilterTextField.setEnabled(option == Usage_Backup && ids == null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        QueueManagerNameLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        QueueNameLabel = new javax.swing.JLabel();
        OkButton = new javax.swing.JButton();
        CancelButton = new javax.swing.JButton();
        FilePathTextField = new javax.swing.JTextField();
        BrowseButton = new javax.swing.JButton();
        ProgressBar = new javax.swing.JProgressBar();
        OptionPanel = new javax.swing.JPanel();
        FilterLabel = new javax.swing.JLabel();
        FilterTextField = new javax.swing.JTextField();
        RemoveDLHCheckBox = new javax.swing.JCheckBox();
        CompressCheckBox = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);
        setResizable(false);

        jLabel1.setText("Queue manager name:");
        jLabel1.setPreferredSize(new java.awt.Dimension(150, 25));

        QueueManagerNameLabel.setPreferredSize(new java.awt.Dimension(300, 25));

        jLabel2.setText("Queue name:");
        jLabel2.setPreferredSize(new java.awt.Dimension(150, 25));

        QueueNameLabel.setPreferredSize(new java.awt.Dimension(300, 25));

        OkButton.setText("Ok");
        OkButton.setPreferredSize(new java.awt.Dimension(100, 25));
        OkButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OkButtonActionPerformed(evt);
            }
        });

        CancelButton.setText("Cancel");
        CancelButton.setPreferredSize(new java.awt.Dimension(100, 25));
        CancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelButtonActionPerformed(evt);
            }
        });

        FilePathTextField.setEditable(false);
        FilePathTextField.setPreferredSize(new java.awt.Dimension(370, 25));
        FilePathTextField.setRequestFocusEnabled(false);

        BrowseButton.setText("Select file");
        BrowseButton.setPreferredSize(new java.awt.Dimension(100, 25));
        BrowseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BrowseButtonActionPerformed(evt);
            }
        });

        ProgressBar.setToolTipText("");

        FilterLabel.setText("Filter:");

        RemoveDLHCheckBox.setText("Remove DLH");
        RemoveDLHCheckBox.setToolTipText("Only use this option on dead-letter queue messages");
        RemoveDLHCheckBox.setPreferredSize(new java.awt.Dimension(150, 23));

        CompressCheckBox.setText("Compress output file");
        CompressCheckBox.setToolTipText("Gzip output file");
        CompressCheckBox.setPreferredSize(new java.awt.Dimension(150, 23));

        javax.swing.GroupLayout OptionPanelLayout = new javax.swing.GroupLayout(OptionPanel);
        OptionPanel.setLayout(OptionPanelLayout);
        OptionPanelLayout.setHorizontalGroup(
            OptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, OptionPanelLayout.createSequentialGroup()
                .addComponent(FilterLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(FilterTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(RemoveDLHCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(CompressCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        OptionPanelLayout.setVerticalGroup(
            OptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OptionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(OptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(FilterLabel)
                    .addComponent(FilterTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(RemoveDLHCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CompressCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(FilePathTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BrowseButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(ProgressBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(QueueNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(QueueManagerNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(OptionPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(OkButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(QueueManagerNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(QueueNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(FilePathTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BrowseButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(OptionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(OkButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void OkButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OkButtonActionPerformed
        toggleButtons(false);
        boolean isCompress = CompressCheckBox.isSelected();
        boolean removeDLH = RemoveDLHCheckBox.isSelected();
        boolean isAlias = (QueueType)selectedObject.Type == QueueType.Alias;
        String filter = FilterTextField.getText();
        BackupRestoreMessageTask task = new BackupRestoreMessageTask(queueManager, selectedObject.ObjectName, selectedFilePath, ParentJFrame, ProgressBar, ids, this.usageOption, isCompress,isAlias, removeDLH, filter);
        task.AddTaskActionSuccessListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toggleButtons(true);
                if(IsBackup == false){
                    FireActionEvent();
                }
                Close();
            }
        });
        task.AddTaskActionFailListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                toggleButtons(true);
            }
        });
        Thread thread = new Thread(task);
        thread.start();
    }//GEN-LAST:event_OkButtonActionPerformed

    private void CancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelButtonActionPerformed
        Close();
    }//GEN-LAST:event_CancelButtonActionPerformed

    private void BrowseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BrowseButtonActionPerformed
        showFileChooser();
    }//GEN-LAST:event_BrowseButtonActionPerformed

    private void initCustomProperties(){
        String queueManagerName = "";
        try {
            queueManagerName = queueManager.getName();
        } catch (MQException ex) {
            //LogWriter.WriteToLog(ex.getMessage(), LogType.Error);
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        this.QueueManagerNameLabel.setText(queueManagerName);
        this.QueueNameLabel.setText(selectedObject.ObjectName);  
        this.OkButton.setEnabled(false);
        this.ProgressBar.setValue(0);
        this.ProgressBar.setStringPainted(true);
    }
    
    private void toggleButtons(boolean value){
        this.BrowseButton.setEnabled(value);
        this.CancelButton.setEnabled(value);
        this.OkButton.setEnabled(value);
        this.CompressCheckBox.setEnabled(value);
        this.RemoveDLHCheckBox.setEnabled(value);
    }
    
    private void showFileChooser(){
        JFileChooser fileChooser = new JFileChooser();
        FileFilter filter = usageOption == Usage_SaveMsgContent ?  new FileNameExtensionFilter("MQ message content file (*.txt)","txt") : new FileNameExtensionFilter("MQ message file (*.msg)","msg");
        fileChooser.setFileFilter(filter);
        fileChooser.setDialogTitle("Please select a file");
        int returnVal = fileChooser.showDialog(this, "Select");
        if(returnVal == JFileChooser.APPROVE_OPTION){ 
            selectedFilePath = fileChooser.getSelectedFile().getPath();
            selectedFilePath = checkFilePath(selectedFilePath, usageOption == Usage_SaveMsgContent ? "txt" : "msg");
            this.FilePathTextField.setText(selectedFilePath);
            this.OkButton.setEnabled(true);
        }
        else{
            selectedFilePath = "";
            this.FilePathTextField.setText("");
            this.OkButton.setEnabled(false);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BrowseButton;
    private javax.swing.JButton CancelButton;
    private javax.swing.JCheckBox CompressCheckBox;
    private javax.swing.JTextField FilePathTextField;
    private javax.swing.JLabel FilterLabel;
    private javax.swing.JTextField FilterTextField;
    private javax.swing.JButton OkButton;
    private javax.swing.JPanel OptionPanel;
    private javax.swing.JProgressBar ProgressBar;
    private javax.swing.JLabel QueueManagerNameLabel;
    private javax.swing.JLabel QueueNameLabel;
    private javax.swing.JCheckBox RemoveDLHCheckBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
