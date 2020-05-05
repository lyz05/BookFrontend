/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.home999.bookfrontend.Frm;

import cc.home999.bookfrontend.Model.TableModel;
import cc.home999.bookfrontend.Model.UserInfoModel;
import cc.home999.bookfrontend.Model.BookReaderModel;
import cc.home999.bookfrontend.Model.Msg;
import cc.home999.bookfrontend.bean.BookReader;
import cc.home999.bookfrontend.bean.BorrowReader;
import cc.home999.bookfrontend.controller.BorrowController;
import cc.home999.bookfrontend.controller.TableController;
import cc.home999.bookfrontend.controller.UserController;
import cc.home999.bookfrontend.utils.Util4Frm;
import com.alibaba.fastjson.JSONObject;
import java.util.List;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author 叶荣锋 congcong
 */
public class FrmBorrowInformation extends javax.swing.JFrame {

    private JTable nowJTable;
    private final UserController userController = UserController.getInstance();
    private final BorrowController borrowController = BorrowController.getInstance();
    private final TableController tableController = TableController.getInstance();

    /**
     * Creates new form FrmBorrowInformation
     */
    public FrmBorrowInformation() {
        initComponents();
        //jTableHeaderListen();
        //默认界面丑拒，换成Windows默认界面
        Util4Frm.setUI(this);
        jTableSelectionListener(jTable1, lblBack);
        jTableSelectionListener(jTable2, lblBack);
        jTableSelectionListener(jTable3, lblBack);

        UserInfoModel userinfo = userController.userinfo();
        Hello.setText("您好，" + userinfo.getReadername());
        //添加默认按钮
        this.getRootPane().setDefaultButton(Select);
        //refreshBorrowTable();
    }

    private BookReaderModel getbook() {
        return new BookReaderModel(InputBookNo.getText(), InputBookName.getText(), InputAuthor.getText(), InputPublishName.getText(), InputPublishDate_1.getText(), InputPublishDate_2.getText(), jCheckBox1.isSelected());
    }

    /**
     * 重置所有文本框
     */
    private void resetTextfiled() {
        InputBookNo.setText("");
        InputBookName.setText("");
        InputAuthor.setText("");
        InputPublishName.setText("");
        InputPublishDate_1.setText("");
        InputPublishDate_2.setText("");
        jCheckBox1.setSelected(false);
    }

    /**
     * 注册jTable选择行监听器
     *
     * @param jtable 待注册表格
     * @param lblBack 底部标签
     */
    public final void jTableSelectionListener(JTable jtable, JLabel lblBack) {
        jtable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                // do some actions here, for example
                // print first column value from selected row
                Util4Frm.resetBackText(jtable, lblBack);
            }
        });
    }

    /**
     * 获取当前选中书籍的BookNo
     *
     * @return 返回BookNO
     */
    private String getbookno() {
        if (nowJTable.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "请选择一本书", "系统提示", JOptionPane.INFORMATION_MESSAGE);
            return null;
        }
        return (String) nowJTable.getValueAt(nowJTable.getSelectedRow(), 0);
    }

    /**
     * 借书还书操作
     */
    private void borrowandreturn() {
        if (getbookno() == null) {
            return;
        }
        String BookNO = getbookno(), sql;
        Msg msg = null;
        if (btnBorrowReturn.getText().equals("还书")) {
            msg = borrowController.retborrow(BookNO);
        } else {
            msg = borrowController.addborrow(BookNO);
        }
        if (Util4Frm.judgeNull(msg)) {
            return;
        }
        Util4Frm.showMessage(msg);
        if (btnBorrowReturn.getText().equals("还书")) {
            refreshBorrowTable();
        } else {
            refreshBookTable();
        }
    }

    /**
     * 续借操作
     */
    private void renew() {
        if (getbookno() == null) {
            return;
        }
        Msg msg = borrowController.renewborrow(getbookno());
        if (Util4Frm.judgeNull(msg)) {
            return;
        }
        Util4Frm.showMessage(msg);
    }

    /**
     * 刷新当前借阅表
     *
     * @param appendsql 追加的sql
     */
    private void refreshBorrowTable() {
        TableModel borrowreaders = tableController.borrowreaders("Borrow");
        Util4Frm.judgeNull(borrowreaders);
        List<?> borrowReaders = borrowreaders.getRows();
        DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();//创建model
        Vector dataVector = dtm.getDataVector();
        while (dtm.getRowCount() > 0) {
            dtm.removeRow(0);
        }
        for (Object row : borrowReaders) {
            Vector rowData = new Vector();
            BorrowReader borrowReader = JSONObject.parseObject(row.toString(), BorrowReader.class);
            rowData.add(borrowReader.getBookno());
            rowData.add(borrowReader.getBookname());
            rowData.add(borrowReader.getAuthor());
            rowData.add(borrowReader.getPress());
            rowData.add(borrowReader.getBorrowdate());
            rowData.add(borrowReader.getShoulddate());
            rowData.add(borrowReader.getReturndate());
            dtm.addRow(rowData);
        }
        Util4Frm.resizeColumnWidth(jTable1);
        Util4Frm.resetBackText(nowJTable, lblBack);
    }

    /**
     * 刷新历史借阅表
     *
     * @param appendsql 追加的sql
     */
    private void refreshBorrowHistoryTable() {
        TableModel borrowreaders = tableController.borrowreaders("BorrowHistory");
        Util4Frm.judgeNull(borrowreaders);
        List<?> borrowReaders = borrowreaders.getRows();
        DefaultTableModel dtm = (DefaultTableModel) jTable2.getModel();//创建model
        Vector dataVector = dtm.getDataVector();
        while (dtm.getRowCount() > 0) {
            dtm.removeRow(0);
        }
        for (Object row : borrowReaders) {
            Vector rowData = new Vector();
            BorrowReader borrowReader = JSONObject.parseObject(row.toString(), BorrowReader.class);
            rowData.add(borrowReader.getBookno());
            rowData.add(borrowReader.getBookname());
            rowData.add(borrowReader.getAuthor());
            rowData.add(borrowReader.getPress());
            rowData.add(borrowReader.getBorrowdate());
            rowData.add(borrowReader.getShoulddate());
            rowData.add(borrowReader.getReturndate());
            dtm.addRow(rowData);
        }
        Util4Frm.resizeColumnWidth(jTable1);
        Util4Frm.resetBackText(nowJTable, lblBack);
    }

    /**
     * 刷新图书信息表
     *
     * @param appendsql 追加的sql
     */
    private void refreshBookTable() {
        TableModel bookreaders = tableController.bookreaders(getbook());
        if (Util4Frm.judgeNull(bookreaders)) {
            return;
        }
        List<?> bookadmins = bookreaders.getRows();
        DefaultTableModel dtm = (DefaultTableModel) jTable3.getModel();//创建model
        Vector dataVector = dtm.getDataVector();
        while (dtm.getRowCount() > 0) {
            dtm.removeRow(0);
        }
        for (Object row : bookadmins) {
            Vector rowData = new Vector();
            BookReader bookReader = JSONObject.parseObject(row.toString(), BookReader.class);
            rowData.add(bookReader.getBookno());
            rowData.add(bookReader.getBookname());
            rowData.add(bookReader.getAuthor());
            rowData.add(bookReader.getPress());
            rowData.add(bookReader.getPublishdate());
            rowData.add(bookReader.getShopnum());
            rowData.add(bookReader.getCurnum());
            dtm.addRow(rowData);
        }
        Util4Frm.resizeColumnWidth(jTable1);
        Util4Frm.resetBackText(nowJTable, lblBack);
    }

    private void SelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SelectActionPerformed
        // TODO add your handling code here:
        //跳转到图书界面并刷新内容
        if (jTabbedPane.getSelectedIndex() == 2) {
            refreshBookTable();
        } else {
            jTabbedPane.setSelectedIndex(2);
        }
    }//GEN-LAST:event_SelectActionPerformed

    private void RightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RightActionPerformed
        //按下右键按钮
        Util4Frm.moveFormRow(nowJTable, 1);
    }//GEN-LAST:event_RightActionPerformed

    private void btnRenewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRenewActionPerformed
        // TODO add your handling code here:
        //续借按钮
        renew();
        refreshBorrowTable();
    }//GEN-LAST:event_btnRenewActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void LeftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LeftActionPerformed
        // 按下左键按钮
        Util4Frm.moveFormRow(nowJTable, -1);
    }//GEN-LAST:event_LeftActionPerformed

    private void jTabbedPaneStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPaneStateChanged
        // TODO add your handling code here:
        if (jTabbedPane.getSelectedIndex() == 0) {
            btnBorrowReturn.setText("还书");
            btnBorrowReturn.setEnabled(true);
            btnRenew.setEnabled(true);
            nowJTable = jTable1;
            refreshBorrowTable();
        } else if (jTabbedPane.getSelectedIndex() == 1) {
            btnBorrowReturn.setEnabled(false);
            btnRenew.setEnabled(false);
            nowJTable = jTable2;
            refreshBorrowHistoryTable();
        } else if (jTabbedPane.getSelectedIndex() == 2) {
            btnBorrowReturn.setText("借书");
            btnBorrowReturn.setEnabled(true);
            btnRenew.setEnabled(false);
            nowJTable = jTable3;
            refreshBookTable();
        }
    }//GEN-LAST:event_jTabbedPaneStateChanged

    private void AlterPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AlterPasswordActionPerformed
        //修改密码按钮
        FrmAlterPassword frame = new FrmAlterPassword();
        frame.setVisible(true);
    }//GEN-LAST:event_AlterPasswordActionPerformed

    private void btnBorrowReturnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrowReturnActionPerformed
        // TODO add your handling code here:
        borrowandreturn();
    }//GEN-LAST:event_btnBorrowReturnActionPerformed

    private void ResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResetActionPerformed
        // TODO add your handling code here:
        resetTextfiled();
        refreshBookTable();
    }//GEN-LAST:event_ResetActionPerformed

    private void FrontActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FrontActionPerformed
        // TODO add your handling code here:
        Util4Frm.moveFormRowToTop(nowJTable, 0);
    }//GEN-LAST:event_FrontActionPerformed

    private void BackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackActionPerformed
        // TODO add your handling code here:
        Util4Frm.moveFormRowToTop(nowJTable, 1);
    }//GEN-LAST:event_BackActionPerformed

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Hello = new javax.swing.JLabel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        BookNo = new javax.swing.JLabel();
        BookName = new javax.swing.JLabel();
        Author = new javax.swing.JLabel();
        InputBookNo = new javax.swing.JTextField();
        InputBookName = new javax.swing.JTextField();
        InputAuthor = new javax.swing.JTextField();
        PublishDate = new javax.swing.JLabel();
        InputPublishDate_1 = new javax.swing.JTextField();
        Press = new javax.swing.JLabel();
        InputPublishName = new javax.swing.JTextField();
        To = new javax.swing.JLabel();
        InputPublishDate_2 = new javax.swing.JTextField();
        jCheckBox1 = new javax.swing.JCheckBox();
        Select = new javax.swing.JButton();
        btnBorrowReturn = new javax.swing.JButton();
        btnRenew = new javax.swing.JButton();
        AlterPassword = new javax.swing.JButton();
        jTabbedPane = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        Right = new javax.swing.JButton();
        Left = new javax.swing.JButton();
        Reset = new javax.swing.JButton();
        Front = new javax.swing.JButton();
        Back = new javax.swing.JButton();
        jToolBar1 = new javax.swing.JToolBar();
        lblBack = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("借阅信息");
        setMinimumSize(new java.awt.Dimension(890, 580));
        setPreferredSize(new java.awt.Dimension(890, 580));

        Hello.setText("你好，");

        jLayeredPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "图书信息 过滤器"));
        jLayeredPane1.setToolTipText("");
        jLayeredPane1.setAlignmentX(10.0F);
        jLayeredPane1.setName(""); // NOI18N
        jLayeredPane1.setPreferredSize(new java.awt.Dimension(623, 186));

        BookNo.setText("图书编号");

        BookName.setText("图书名称");

        Author.setText("作者");

        PublishDate.setText("出版日期");

        Press.setText("出版社");

        To.setText("至");

        jCheckBox1.setText("仅显示可借阅书籍");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jLayeredPane1.setLayer(BookNo, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(BookName, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(Author, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(InputBookNo, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(InputBookName, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(InputAuthor, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(PublishDate, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(InputPublishDate_1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(Press, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(InputPublishName, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(To, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(InputPublishDate_2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jCheckBox1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(BookName)
                            .addComponent(Author))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(InputBookName)
                            .addComponent(InputAuthor)))
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addComponent(BookNo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(InputBookNo, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 267, Short.MAX_VALUE)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(PublishDate)
                    .addComponent(Press))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jLayeredPane1Layout.createSequentialGroup()
                            .addComponent(InputPublishDate_1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(To, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(InputPublishDate_2, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(InputPublishName))
                    .addComponent(jCheckBox1))
                .addGap(57, 57, 57))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BookNo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(InputBookNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Press)
                    .addComponent(InputPublishName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BookName)
                    .addComponent(InputBookName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PublishDate)
                    .addComponent(To)
                    .addComponent(InputPublishDate_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(InputPublishDate_2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(InputAuthor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Author)
                    .addComponent(jCheckBox1))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        BookNo.getAccessibleContext().setAccessibleName("");
        BookNo.getAccessibleContext().setAccessibleDescription("");
        BookName.getAccessibleContext().setAccessibleName("");

        Select.setText("查询");
        Select.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SelectActionPerformed(evt);
            }
        });

        btnBorrowReturn.setText("借书");
        btnBorrowReturn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrowReturnActionPerformed(evt);
            }
        });

        btnRenew.setText("续借");
        btnRenew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRenewActionPerformed(evt);
            }
        });

        AlterPassword.setText("修改密码");
        AlterPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AlterPasswordActionPerformed(evt);
            }
        });

        jTabbedPane.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPaneStateChanged(evt);
            }
        });

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "图书编号", "图书名称", "作者", "出版社", "借阅日期", "应归还日期", "归还日期"
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
        jScrollPane1.setViewportView(jTable1);

        jTabbedPane.addTab("当前借阅", jScrollPane1);

        jTable2.setAutoCreateRowSorter(true);
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "图书编号", "图书名称", "作者", "出版社", "借阅日期", "应归还日期", "归还日期"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.setToolTipText("");
        jTable2.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(jTable2);

        jTabbedPane.addTab("历史借阅", jScrollPane2);

        jTable3.setAutoCreateRowSorter(true);
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "图书编号", "图书名称", "作者", "出版社", "出版日期", "入库数量", "在库数量"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable3.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane3.setViewportView(jTable3);

        jTabbedPane.addTab("图书信息", jScrollPane3);

        Right.setText(">");
        Right.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RightActionPerformed(evt);
            }
        });

        Left.setText("<");
        Left.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LeftActionPerformed(evt);
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLayeredPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 873, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Hello, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jTabbedPane)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Select)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBorrowReturn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRenew)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Reset)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(AlterPassword)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Front)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Left)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Right)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Back)))
                .addContainerGap())
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(Hello, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Left)
                    .addComponent(Right)
                    .addComponent(Select)
                    .addComponent(btnBorrowReturn)
                    .addComponent(btnRenew)
                    .addComponent(AlterPassword)
                    .addComponent(Reset, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Front)
                    .addComponent(Back))
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLayeredPane1.getAccessibleContext().setAccessibleName("过滤器1");
        btnRenew.getAccessibleContext().setAccessibleName("");
        btnRenew.getAccessibleContext().setAccessibleDescription("");
        jTabbedPane.getAccessibleContext().setAccessibleDescription("");

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(FrmBorrowInformation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmBorrowInformation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmBorrowInformation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmBorrowInformation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmBorrowInformation().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AlterPassword;
    private javax.swing.JLabel Author;
    private javax.swing.JButton Back;
    private javax.swing.JLabel BookName;
    private javax.swing.JLabel BookNo;
    private javax.swing.JButton Front;
    private javax.swing.JLabel Hello;
    private javax.swing.JTextField InputAuthor;
    private javax.swing.JTextField InputBookName;
    private javax.swing.JTextField InputBookNo;
    private javax.swing.JTextField InputPublishDate_1;
    private javax.swing.JTextField InputPublishDate_2;
    private javax.swing.JTextField InputPublishName;
    private javax.swing.JButton Left;
    private javax.swing.JLabel Press;
    private javax.swing.JLabel PublishDate;
    private javax.swing.JButton Reset;
    private javax.swing.JButton Right;
    private javax.swing.JButton Select;
    private javax.swing.JLabel To;
    private javax.swing.JButton btnBorrowReturn;
    private javax.swing.JButton btnRenew;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lblBack;
    // End of variables declaration//GEN-END:variables

}
