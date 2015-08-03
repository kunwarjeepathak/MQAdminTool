/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.Dialogs;

import MQApi.Logs.LogWriter;
import MQApi.PCF.MQPCF;
import MQApi.QueryModel.MQQueueListResult;
import MQApi.QueryModel.MQQueueStatusListResult;
import MQApi.TextWriter;
import UI.Helpers.DateTimeHelper;
import UI.Helpers.TableHelper;
import com.ibm.mq.MQException;
import com.ibm.mq.MQQueueManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.NumberFormatter;

/**
 *
 * @author jzhou
 */
public class QueueMonitorDialog extends DialogBase {

    MQQueueManager queueManager;
    Timer timer = new Timer(true);
    String searchString;
    String savePath;
    public QueueMonitorDialog(java.awt.Frame parent, boolean modal, MQQueueManager queueManager) {
        super(parent, modal, queueManager, null);
        this.setAlwaysOnTop(false);
        this.queueManager = queueManager;
        initComponents();
        initCustomProperties();
        
    }
    
    private void initCustomProperties(){
        this.setTitle("Queue monitor");
        this.setIconImage(iconManager.MonitorIcon().getImage());
        String queueManagerName = "";
        try {
            queueManagerName = queueManager.getName();
        } catch (MQException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        };
        QueueManagerLabel.setText(queueManagerName);
        this.ProgressBar.setVisible(false);
        this.StopButton.setEnabled(false);
        this.ProgressBar.setIndeterminate(true);
         this.StartButton.setEnabled(false);
        
        TableHelper.InitTable(ContentTable);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        QueueManagerLabel = new javax.swing.JLabel();
        jToolBar1 = new javax.swing.JToolBar();
        jLabel2 = new javax.swing.JLabel();
        QueueNameTextField = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        jLabel3 = new javax.swing.JLabel();
        IntervalSpinner = new javax.swing.JSpinner();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        SearchButton = new javax.swing.JButton();
        StartButton = new javax.swing.JButton();
        StopButton = new javax.swing.JButton();
        StatusLabel = new javax.swing.JLabel();
        ProgressBar = new javax.swing.JProgressBar();
        jScrollPane2 = new javax.swing.JScrollPane();
        ContentTable = new javax.swing.JTable(){
            @Override
            public Class getColumnClass(int column)
            {
                int rowCount = getRowCount();
                Object value = null;
                for(int i = 0 ; i < rowCount; i++){
                    value = getValueAt(i, column);
                    if(value != null){
                        return value.getClass();
                    }
                }
                return Object.class;
            }
        };

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModal(false);
        setPreferredSize(new java.awt.Dimension(550, 435));
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setPreferredSize(new java.awt.Dimension(580, 40));

        jLabel1.setText("Queue manager : ");
        jLabel1.setPreferredSize(new java.awt.Dimension(150, 25));

        QueueManagerLabel.setPreferredSize(new java.awt.Dimension(150, 25));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(QueueManagerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(338, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(QueueManagerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jToolBar1.setBorder(null);
        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);
        jToolBar1.setPreferredSize(new java.awt.Dimension(100, 45));

        jLabel2.setText("Queue:");
        jLabel2.setPreferredSize(new java.awt.Dimension(50, 30));
        jToolBar1.add(jLabel2);

        QueueNameTextField.setPreferredSize(new java.awt.Dimension(250, 30));
        QueueNameTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                QueueNameTextFieldKeyPressed(evt);
            }
        });
        jToolBar1.add(QueueNameTextField);
        jToolBar1.add(jSeparator1);

        jLabel3.setText("Interval:");
        jLabel3.setPreferredSize(new java.awt.Dimension(50, 30));
        jToolBar1.add(jLabel3);

        IntervalSpinner.setMinimumSize(new java.awt.Dimension(60, 30));
        IntervalSpinner.setPreferredSize(new java.awt.Dimension(50, 30));
        IntervalSpinner.setModel(new SpinnerNumberModel(6, 1, 10, 1));
        ((NumberFormatter)((JSpinner.NumberEditor)this.IntervalSpinner.getEditor()).getTextField().getFormatter()).setAllowsInvalid(false);
        ((JSpinner.NumberEditor)this.IntervalSpinner.getEditor()).getTextField().setHorizontalAlignment(JTextField.LEFT);
        jToolBar1.add(IntervalSpinner);
        jToolBar1.add(jSeparator2);

        SearchButton.setIcon(iconManager.SearchIcon()
        );
        SearchButton.setToolTipText("Search");
        SearchButton.setFocusable(false);
        SearchButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        SearchButton.setPreferredSize(new java.awt.Dimension(30, 30));
        SearchButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        SearchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(SearchButton);

        StartButton.setIcon(iconManager.StartIcon()
        );
        StartButton.setToolTipText("Start");
        StartButton.setFocusable(false);
        StartButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        StartButton.setPreferredSize(new java.awt.Dimension(30, 30));
        StartButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        StartButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StartButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(StartButton);

        StopButton.setIcon(iconManager.StopIcon()
        );
        StopButton.setToolTipText("Stop");
        StopButton.setFocusable(false);
        StopButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        StopButton.setPreferredSize(new java.awt.Dimension(30, 30));
        StopButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        StopButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StopButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(StopButton);

        StatusLabel.setPreferredSize(new java.awt.Dimension(200, 25));

        ProgressBar.setPreferredSize(new java.awt.Dimension(150, 25));

        ContentTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(ContentTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(StatusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 812, Short.MAX_VALUE)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(StatusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void StartButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StartButtonActionPerformed
        if(this.savePath == null){
            JFileChooser fileChooser = new JFileChooser();
            FileFilter filter = new FileNameExtensionFilter("csv files","csv");
            fileChooser.setFileFilter(filter);
            int returnVal = fileChooser.showSaveDialog(this);
            if(returnVal == JFileChooser.APPROVE_OPTION){
                this.savePath = fileChooser.getSelectedFile().getPath();
            }
            else{
                this.savePath = null;
            }
        }
        if(this.savePath != null){  
            
            toggleButton(true);
            int intev = (int)this.IntervalSpinner.getValue();
            final String search = this.searchString;
            timer.schedule(new TimerTask() {

                @Override
                public void run() {
                    ProgressBar.setVisible(true);
                    updateTable(search, true);
                    StatusLabel.setText("Last updated : " + DateTimeHelper.GetCurrentTimeStamp());
                }
            }, 0, intev * 1000 * 60);
        }
        
        
    }//GEN-LAST:event_StartButtonActionPerformed

    private void StopButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StopButtonActionPerformed
        timer.cancel();
        timer = new Timer(true);
        this.StatusLabel.setText("");
        toggleButton(false);
    }//GEN-LAST:event_StopButtonActionPerformed

    private void SearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchButtonActionPerformed
        String queueName = this.QueueNameTextField.getText();
        if(queueName != null){
            queueName = queueName.trim();
        }
        if(queueName == null || queueName.isEmpty()){
            queueName = "*";
        }
        
        final String search = queueName;
        Thread thread = new Thread(new Runnable() {
            
            @Override
            public void run() {
                boolean hasResult = updateTable(search, false);
                StartButton.setEnabled(hasResult);
                if(hasResult){
                    searchString = search;
                    QueueNameTextField.setText(search);
                }
                else{
                    searchString = null;                   
                }
                savePath = null;
            }
        });
        thread.start();
    }//GEN-LAST:event_SearchButtonActionPerformed

    private void QueueNameTextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_QueueNameTextFieldKeyPressed
        this.StartButton.setEnabled(false);
    }//GEN-LAST:event_QueueNameTextFieldKeyPressed
    
    private void toggleButton(boolean start){
        this.StartButton.setEnabled(!start);
        this.QueueNameTextField.setEnabled(!start);
        this.IntervalSpinner.setEnabled(!start);
        this.StopButton.setEnabled(start);
        this.SearchButton.setEnabled(!start);
    }
    
    private boolean updateTable(String queueName, boolean monitorStart){
        boolean gotResult = false;
        MQQueueStatusListResult result = MQPCF.GetQueueStatusList(queueManager, queueName);
        if(result.QuerySuccess && result.DataModels.size() > 0){
            TableHelper.UpdateContentTable(ContentTable, result.DataModels);
            if(monitorStart){
                try {
                    TextWriter.WriteModelToCSV((ArrayList<Object>)(Object)result.DataModels, savePath, true);
                } catch (IOException ex) {
                    timer.cancel();
                    LogWriter.WriteToLog(ex.fillInStackTrace());
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    Close();
                }
            }
            gotResult = true;
        }
        else{
            TableHelper.InitTable(ContentTable);
        }
        this.ProgressBar.setVisible(false);
        return gotResult;
    }
    
    


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable ContentTable;
    private javax.swing.JSpinner IntervalSpinner;
    private javax.swing.JProgressBar ProgressBar;
    private javax.swing.JLabel QueueManagerLabel;
    private javax.swing.JTextField QueueNameTextField;
    private javax.swing.JButton SearchButton;
    private javax.swing.JButton StartButton;
    private javax.swing.JLabel StatusLabel;
    private javax.swing.JButton StopButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables
}
