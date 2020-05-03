/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.home999.bookfrontend.Frm;

import cc.home999.bookfrontend.Model.Msg;
import cc.home999.bookfrontend.Model.TableModel;
import cc.home999.bookfrontend.bean.Reader;
import cc.home999.bookfrontend.bean.ReaderAdmin;
import cc.home999.bookfrontend.controller.ReaderController;
import cc.home999.bookfrontend.controller.TableController;
import cc.home999.bookfrontend.controller.UserController;
import cc.home999.bookfrontend.utils.Util4Frm;
import com.alibaba.fastjson.JSONObject;
import java.util.List;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author 马立健 congcong
 */
public class FrmReaderInformation extends javax.swing.JFrame {

    TableController tableController = TableController.getInstance();
    ReaderController readerController = ReaderController.getInstance();
    UserController userController = UserController.getInstance();

    /**
     * Creates new form FrmReaderInformation
     */
    public FrmReaderInformation() {
        initComponents();
        ChooseSex.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"", "男", "女"}));
        jTableSelectionListener();
        //默认界面丑拒，换成Windows默认界面
        Util4Frm.setUI(this);
        //添加默认按钮
        this.getRootPane().setDefaultButton(Renovate);
        RefreshReaderInformation();
    }

    /**
     * 注册jTable选择行监听器
     */
    private void jTableSelectionListener() {
        jTable1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                // do some actions here, for example
                // print first column value from selected row
                Util4Frm.resetBackText(jTable1, lblBack);
            }
        });
    }

    private Reader getReader() {
        return new Reader(InputReaderNo.getText(), InputReaderName.getText(), String.valueOf(ChooseSex.getSelectedItem()), InputIdNum.getText(), InputWorkUnit.getText());
    }

    /**
     * 编辑框是否为空
     *
     * @return 是否为空
     */
    private boolean textFiledIsNull() {
        return InputReaderNo.getText().equals("") || InputReaderName.getText().equals("") || InputIdNum.getText().equals("") || InputWorkUnit.getText().equals("");
    }

    /**
     * 刷新读者信息
     *
     * @param appendsql 追加的sql文本
     */
    //刷新、查询读者信息
    private void RefreshReaderInformation() {
        TableModel tableModel = tableController.readeradmins(getReader());
        if (Util4Frm.judgenull(tableModel)) {
            return;
        }
        List<?> readeradmins = tableModel.getRows();

        DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();//创建model
        Vector dataVector = dtm.getDataVector();
        while (dtm.getRowCount() > 0) {
            dtm.removeRow(0);
        }
        for (Object row : readeradmins) {
            Vector rowData = new Vector();
            ReaderAdmin bookAdmin = JSONObject.parseObject(row.toString(), ReaderAdmin.class);
            rowData.add(bookAdmin.getReaderno());
            rowData.add(bookAdmin.getReadername());
            rowData.add(bookAdmin.getSex());
            rowData.add(bookAdmin.getIdentitycard());
            rowData.add(bookAdmin.getWorkunit());
            rowData.add(bookAdmin.getTotalborrownum());
            rowData.add(bookAdmin.getNotreturnnum());
            dtm.addRow(rowData);
        }
        Util4Frm.resizeColumnWidth(jTable1);
        //刷新底部状态栏的标签显示
        Util4Frm.resetBackText(jTable1, lblBack);
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
        IdNum = new javax.swing.JLabel();
        InputIdNum = new javax.swing.JTextField();
        WorkUnit = new javax.swing.JLabel();
        InputWorkUnit = new javax.swing.JTextField();
        ReaderNo = new javax.swing.JLabel();
        ReaderName = new javax.swing.JLabel();
        Sex = new javax.swing.JLabel();
        InputReaderNo = new javax.swing.JTextField();
        InputReaderName = new javax.swing.JTextField();
        ChooseSex = new javax.swing.JComboBox();
        Add = new javax.swing.JButton();
        Delete = new javax.swing.JButton();
        Alter = new javax.swing.JButton();
        Left = new javax.swing.JButton();
        Right = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        Renovate = new javax.swing.JButton();
        ResetPassword = new javax.swing.JButton();
        Reset = new javax.swing.JButton();
        Front = new javax.swing.JButton();
        Back = new javax.swing.JButton();
        jToolBar1 = new javax.swing.JToolBar();
        lblBack = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("读者信息管理 - 图书借阅管理系统");
        setMinimumSize(new java.awt.Dimension(890, 560));
        setPreferredSize(new java.awt.Dimension(890, 560));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("筛选模式"));

        IdNum.setText("身份证号");

        InputIdNum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputIdNumActionPerformed(evt);
            }
        });

        WorkUnit.setText("工作单位");

        ReaderNo.setText("读者号");

        ReaderName.setText("姓名");

        Sex.setText("性别");

        InputReaderNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputReaderNoActionPerformed(evt);
            }
        });

        InputReaderName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputReaderNameActionPerformed(evt);
            }
        });

        ChooseSex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChooseSexActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ReaderNo)
                    .addComponent(ReaderName, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Sex, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(ChooseSex, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(InputReaderNo, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 385, Short.MAX_VALUE)
                        .addComponent(IdNum)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(InputIdNum, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(InputReaderName, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(WorkUnit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(InputWorkUnit, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ReaderNo)
                    .addComponent(InputReaderNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(InputIdNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(IdNum))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(InputWorkUnit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(WorkUnit))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(InputReaderName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ReaderName)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ChooseSex, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Sex))
                .addGap(66, 66, 66))
        );

        ChooseSex.getAccessibleContext().setAccessibleName("");

        Add.setText("添加");
        Add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddActionPerformed(evt);
            }
        });

        Delete.setText("删除");
        Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteActionPerformed(evt);
            }
        });

        Alter.setText("修改");
        Alter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AlterActionPerformed(evt);
            }
        });

        Left.setText("<");
        Left.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LeftActionPerformed(evt);
            }
        });

        Right.setText(">");
        Right.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RightActionPerformed(evt);
            }
        });

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "读者号", "姓名", "性别", "身份证号", "工作单位", "总借书数量", "未归还数量"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane3.setViewportView(jTable1);

        Renovate.setText("查询");
        Renovate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RenovateActionPerformed(evt);
            }
        });

        ResetPassword.setText("重置读者密码");
        ResetPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResetPasswordActionPerformed(evt);
            }
        });

        Reset.setText("重置");
        Reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResetActionPerformed(evt);
            }
        });

        Front.setText("<<");
        Front.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FrontActionPerformed(evt);
            }
        });

        Back.setText(">>");
        Back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackActionPerformed(evt);
            }
        });

        jToolBar1.setRollover(true);

        lblBack.setText("准备就绪");
        jToolBar1.add(lblBack);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Renovate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Add)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Delete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Alter, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Reset)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ResetPassword)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 186, Short.MAX_VALUE)
                        .addComponent(Front)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Left)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Right)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Back)
                        .addGap(4, 4, 4))
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Add, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Delete)
                        .addComponent(Renovate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Alter)
                        .addComponent(Reset)
                        .addComponent(ResetPassword))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Left)
                        .addComponent(Right)
                        .addComponent(Front)
                        .addComponent(Back)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void ChooseSexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChooseSexActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ChooseSexActionPerformed

    private void InputIdNumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputIdNumActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InputIdNumActionPerformed

    private void InputReaderNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputReaderNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InputReaderNameActionPerformed

    /**
     * 添加记录
     */
    private void AddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddActionPerformed
        // TODO add your handling code here:
        //若编辑框是否为空，系统提示
        Msg msg = readerController.addreader(getReader());
        if (Util4Frm.judgenull(msg)) {
            return;
        }
        if (msg.getCode() == 200) {
            JOptionPane.showMessageDialog(null, msg.getMessage(), "系统提示", JOptionPane.INFORMATION_MESSAGE);
            //重置所有文本框
            resetTextfiled();
            //刷新、查询读者信息
            RefreshReaderInformation();
        } else {
            JOptionPane.showMessageDialog(null, msg.getMessage(), "系统提示", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_AddActionPerformed


    private void RenovateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RenovateActionPerformed
        // TODO add your handling code here:
        //刷新、查询读者信息
        RefreshReaderInformation();
    }//GEN-LAST:event_RenovateActionPerformed

    private void LeftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LeftActionPerformed
        // 按下左键按钮
        Util4Frm.moveFormRow(jTable1, -1);
    }//GEN-LAST:event_LeftActionPerformed

    private void RightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RightActionPerformed
        //按下右键按钮
        Util4Frm.moveFormRow(jTable1, 1);
    }//GEN-LAST:event_RightActionPerformed

    /**
     * 获取当前选中读者的ReaderNo
     *
     * @return 返回ReaderNo
     */
    private String getreaderno() {
        if (jTable1.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "请选择一位读者", "系统提示", JOptionPane.INFORMATION_MESSAGE);
            return null;
        }
        return (String) jTable1.getValueAt(jTable1.getSelectedRow(), 0);
    }

    /**
     * 重置密码按钮被按下
     */
    private void ResetPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResetPasswordActionPerformed
        // TODO add your handling code here:
        if (getreaderno() == null || !Util4Frm.confirmresetpwd()) {
            return;
        }
        Msg msg = userController.resetpwd(getreaderno());
        if (msg.getCode() == 200) {
            JOptionPane.showMessageDialog(null, msg.getMessage(), "系统提示", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, msg.getMessage(), "系统提示", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_ResetPasswordActionPerformed

    /**
     * 读记录到编辑框
     */
    private void getdatatotextfiled() {
        InputReaderNo.setText((String) jTable1.getValueAt(jTable1.getSelectedRow(), 0));
        InputReaderName.setText((String) jTable1.getValueAt(jTable1.getSelectedRow(), 1));
        if (String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 2)).equals("男")) {
            ChooseSex.setSelectedIndex(1);
        } else {
            ChooseSex.setSelectedIndex(2);
        }
        InputIdNum.setText((String) jTable1.getValueAt(jTable1.getSelectedRow(), 3));
        InputWorkUnit.setText((String) jTable1.getValueAt(jTable1.getSelectedRow(), 4));
    }

    /**
     * 重置所有文本框
     */
    private void resetTextfiled() {
        InputReaderNo.setText("");
        InputReaderName.setText("");
        InputIdNum.setText("");
        InputWorkUnit.setText("");
        ChooseSex.setSelectedIndex(0);
    }

    /**
     * 修改保存按钮被点击
     */
    private void AlterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AlterActionPerformed
        // TODO add your handling code here:
        if (Alter.getText().equals("修改")) {
            if (getreaderno() == null) {
                return;
            }
            String ReaderNO = getreaderno();
            //读记录到编辑框
            getdatatotextfiled();
            //锁主键（读者号的文本框）
            Util4Frm.locktextfiled(InputReaderNo);
            jPanel1.setBorder(BorderFactory.createTitledBorder("编辑模式"));
            //把修改按钮改成保存按钮
            Alter.setText("保存");
            //锁定部分按钮
            Reset.setEnabled(false);
            Delete.setEnabled(false);
            Add.setEnabled(false);
        } else {
            Msg msg = readerController.editreader(getReader());
            if (Util4Frm.judgenull(msg)) {
                return;
            }
            if (msg.getCode() == 200) {
                JOptionPane.showMessageDialog(null, msg.getMessage(), "系统提示", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, msg.getMessage(), "系统提示", JOptionPane.ERROR_MESSAGE);
            }
            //重置所有文本框
            resetTextfiled();
            //解锁主键（读者号的文本框）
            Util4Frm.unlocktextfiled(InputReaderNo);
            //刷新读者信息
            RefreshReaderInformation();
            jPanel1.setBorder(BorderFactory.createTitledBorder("筛选模式"));
            //把保存按钮改回修改按钮
            Alter.setText("修改");
            //解锁按钮
            Reset.setEnabled(true);
            Delete.setEnabled(true);
            Add.setEnabled(true);
        }
    }//GEN-LAST:event_AlterActionPerformed
    /**
     * 删除记录
     */
    private void DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteActionPerformed
        // TODO add your handling code here:
        Msg msg = readerController.delreader(getreaderno());
        if (Util4Frm.judgenull(msg)) {
            return;
        }
        if (msg.getCode() == 200) {
            JOptionPane.showMessageDialog(null, msg.getMessage(), "系统提示", JOptionPane.INFORMATION_MESSAGE);
            //刷新、查询读者信息
            RefreshReaderInformation();
        } else {
            JOptionPane.showMessageDialog(null, msg.getMessage(), "系统提示", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_DeleteActionPerformed

    private void ResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResetActionPerformed
        // TODO add your handling code here:
        //重置所有文本框
        resetTextfiled();
        //刷新、查询读者信息
        RefreshReaderInformation();
    }//GEN-LAST:event_ResetActionPerformed

    private void FrontActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FrontActionPerformed
        // TODO add your handling code here:
        Util4Frm.moveFormRowToTop(jTable1, 0);
    }//GEN-LAST:event_FrontActionPerformed

    private void BackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackActionPerformed
        // TODO add your handling code here:
        Util4Frm.moveFormRowToTop(jTable1, 1);
    }//GEN-LAST:event_BackActionPerformed

    private void InputReaderNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputReaderNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InputReaderNoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmReaderInformation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmReaderInformation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmReaderInformation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmReaderInformation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmReaderInformation().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Add;
    private javax.swing.JButton Alter;
    private javax.swing.JButton Back;
    private javax.swing.JComboBox ChooseSex;
    private javax.swing.JButton Delete;
    private javax.swing.JButton Front;
    private javax.swing.JLabel IdNum;
    private javax.swing.JTextField InputIdNum;
    private javax.swing.JTextField InputReaderName;
    private javax.swing.JTextField InputReaderNo;
    private javax.swing.JTextField InputWorkUnit;
    private javax.swing.JButton Left;
    private javax.swing.JLabel ReaderName;
    private javax.swing.JLabel ReaderNo;
    private javax.swing.JButton Renovate;
    private javax.swing.JButton Reset;
    private javax.swing.JButton ResetPassword;
    private javax.swing.JButton Right;
    private javax.swing.JLabel Sex;
    private javax.swing.JLabel WorkUnit;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lblBack;
    // End of variables declaration//GEN-END:variables

}
