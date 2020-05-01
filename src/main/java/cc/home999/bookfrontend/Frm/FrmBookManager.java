/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.home999.bookfrontend.Frm;

import cc.home999.bookfrontend.Model.Msg;
import cc.home999.bookfrontend.Model.TableModel;
import cc.home999.bookfrontend.bean.Book;
import cc.home999.bookfrontend.bean.BookAdmin;
import cc.home999.bookfrontend.controller.BookController;
import cc.home999.bookfrontend.controller.TableController;
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
 * @author 叶荣锋
 */
public class FrmBookManager extends javax.swing.JFrame {

    TableController tableController = TableController.getInstance();
    BookController bookController = BookController.getInstance();

    /**
     * Creates new form FrmBookManager
     */
    public FrmBookManager() {
        initComponents();
        jTableSelectionListener();
        //默认界面丑拒，换成Windows默认界面
        Util4Frm.setUI(this);
        RefreshBookInformation();
    }

    /**
     * 注册jTable选择行监听器
     */
    private void jTableSelectionListener() {
        jTable1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                // do some actions here, for example
                // print first column value from selected row
                Util4Frm.resetBackText(jTable1, lblBack);
            }
        });
    }

    /**
     * 获取当前选中书籍的BookNo
     *
     * @return
     */
    private String getbookno() {
        if (jTable1.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "请选择一本书", "系统提示", JOptionPane.INFORMATION_MESSAGE);
            return null;
        }
        return (String) jTable1.getValueAt(jTable1.getSelectedRow(), 0);
    }

    /**
     * 将编辑框中的书籍信息封装成Book类
     *
     * @return
     */
    private Book getbook() {
        return new Book(InputBookNum.getText(), InputBookName.getText(), InputAuthor.getText(), InputPress.getText(), InputPrice.getText(), InputPublishdate.getText(), InputShopNum.getText());
    }

    /**
     * 判断文本框是否为空
     *
     * @return
     */
    private boolean textFiledIsNull() {
        return InputBookNum.getText().equals("") || InputBookName.getText().equals("") || InputAuthor.getText().equals("") || InputPress.getText().equals("") || InputPrice.getText().equals("") || InputPublishdate.getText().equals("") || InputShopNum.getText().equals("");
    }

    /**
     * 重置编辑框
     */
    private void resetTextfiled() {
        InputBookNum.setText("");
        InputBookName.setText("");
        InputAuthor.setText("");
        InputPress.setText("");
        InputPrice.setText("");
        InputPublishdate.setText("");
        InputShopNum.setText("");
    }

    /**
     * 读记录到文本框
     */
//    获取jtable 中的值：
//　　　　jtable1.getValueAt();
    private void getdatatotextfiled() {
        InputBookNum.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
        InputBookName.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString());
        InputAuthor.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 2).toString());
        InputPress.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 3).toString());
        InputPrice.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 4).toString());
        InputPublishdate.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 5).toString());
        InputShopNum.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 6).toString());
    }

    /**
     * 查询图书信息
     *
     * @param appendsql 追加的sql
     */
    private void RefreshBookInformation() {
        TableModel tableModel = tableController.bookadmins(getbook());
        List<?> bookadmins = tableModel.getRows();

        DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();//创建model
        Vector dataVector = dtm.getDataVector();
        while (dtm.getRowCount() > 0) {
            dtm.removeRow(0);
        }
        for (Object row : bookadmins) {
            Vector rowData = new Vector();
            BookAdmin bookAdmin = JSONObject.parseObject(row.toString(), BookAdmin.class);
            rowData.add(bookAdmin.getBookno());
            rowData.add(bookAdmin.getBookname());
            rowData.add(bookAdmin.getAuthor());
            rowData.add(bookAdmin.getPress());
            rowData.add(bookAdmin.getPrice());
            rowData.add(bookAdmin.getPublishdate());
            rowData.add(bookAdmin.getShopnum());
            rowData.add(bookAdmin.getCurnum());
            dtm.addRow(rowData);
        }
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

        Delete = new javax.swing.JButton();
        Refresh = new javax.swing.JButton();
        Left = new javax.swing.JButton();
        Right = new javax.swing.JButton();
        Add = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        BookNo = new javax.swing.JLabel();
        BookName = new javax.swing.JLabel();
        Author = new javax.swing.JLabel();
        InputBookNum = new javax.swing.JTextField();
        InputBookName = new javax.swing.JTextField();
        InputAuthor = new javax.swing.JTextField();
        Price = new javax.swing.JLabel();
        PublishDate = new javax.swing.JLabel();
        StockInNum = new javax.swing.JLabel();
        InputPrice = new javax.swing.JTextField();
        InputPublishdate = new javax.swing.JTextField();
        InputShopNum = new javax.swing.JTextField();
        Press = new javax.swing.JLabel();
        InputPress = new javax.swing.JTextField();
        Alter = new javax.swing.JButton();
        Reset = new javax.swing.JButton();
        Front = new javax.swing.JButton();
        Back = new javax.swing.JButton();
        jToolBar1 = new javax.swing.JToolBar();
        lblBack = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("图书信息管理 - 图书借阅管理系统");
        setMinimumSize(new java.awt.Dimension(890, 560));

        Delete.setText("删除");
        Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteActionPerformed(evt);
            }
        });

        Refresh.setText("查询");
        Refresh.setToolTipText("");
        Refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RefreshActionPerformed(evt);
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

        Add.setText("添加");
        Add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddActionPerformed(evt);
            }
        });

        jScrollPane2.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jScrollPane2PropertyChange(evt);
            }
        });

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "图书编号", "图书名称", "作者", "出版社", "价格", "出版日期", "入库数量", "在库数量"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("筛选模式（左栏信息可筛选）"));

        BookNo.setText("图书编号");

        BookName.setText("图书名称");

        Author.setText("作者");

        InputBookNum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputBookNumActionPerformed(evt);
            }
        });

        InputBookName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputBookNameActionPerformed(evt);
            }
        });

        InputAuthor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputAuthorActionPerformed(evt);
            }
        });

        Price.setText("价格");

        PublishDate.setText("出版日期");

        StockInNum.setText("入库数量");

        InputPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputPriceActionPerformed(evt);
            }
        });

        Press.setText("出版社");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Press)
                        .addGap(18, 18, 18)
                        .addComponent(InputPress, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(BookName))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Author, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(BookNo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(InputBookName, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(InputAuthor, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(InputBookNum, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(PublishDate)
                            .addComponent(Price))
                        .addGap(21, 21, 21))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(StockInNum)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(InputShopNum)
                    .addComponent(InputPublishdate)
                    .addComponent(InputPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Price)
                            .addComponent(InputPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(PublishDate)
                            .addComponent(InputPublishdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(StockInNum)
                            .addComponent(InputShopNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BookNo)
                            .addComponent(InputBookNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BookName)
                            .addComponent(InputBookName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Author)
                            .addComponent(InputAuthor, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(InputPress, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Press))
                .addGap(20, 20, 20))
        );

        Press.getAccessibleContext().setAccessibleName("");

        Alter.setText("修改");
        Alter.setToolTipText("");
        Alter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AlterActionPerformed(evt);
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
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(Refresh)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Add)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Delete)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Alter)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Reset)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 333, Short.MAX_VALUE)
                                .addComponent(Front)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Left)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Right)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Back)))
                        .addGap(10, 10, 10))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Left)
                        .addComponent(Right)
                        .addComponent(Front)
                        .addComponent(Back))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Add)
                        .addComponent(Delete)
                        .addComponent(Refresh)
                        .addComponent(Alter)
                        .addComponent(Reset, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteActionPerformed
        // TODO add your handling code here:
        Msg msg = bookController.delbook(getbookno());
        if (msg.getCode() == 200) {
            JOptionPane.showMessageDialog(null, msg.getMessage(), "系统提示", JOptionPane.INFORMATION_MESSAGE);
            RefreshBookInformation();
        } else {
            JOptionPane.showMessageDialog(null, msg.getMessage(), "系统提示", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_DeleteActionPerformed

    private void RefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RefreshActionPerformed
        // TODO add your handling code here:
        RefreshBookInformation();
    }//GEN-LAST:event_RefreshActionPerformed

    private void InputBookNumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputBookNumActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InputBookNumActionPerformed

    private void InputBookNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputBookNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InputBookNameActionPerformed

    private void InputAuthorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputAuthorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InputAuthorActionPerformed

    private void AddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddActionPerformed
        // TODO add your handling code here:
        Msg msg = bookController.addbook(getbook());
        if (msg.getCode() == 200) {
            JOptionPane.showMessageDialog(null, msg.getMessage(), "系统提示", JOptionPane.INFORMATION_MESSAGE);
            resetTextfiled();
            RefreshBookInformation();
        } else {
            JOptionPane.showMessageDialog(null, msg.getMessage(), "系统提示", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_AddActionPerformed

    /**
     * 向上移动
     *
     * @param evt
     */
    private void LeftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LeftActionPerformed
        Util4Frm.moveFormRow(jTable1, -1);
    }//GEN-LAST:event_LeftActionPerformed

    /**
     * 向下移动
     *
     * @param evt
     */
    private void RightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RightActionPerformed
        Util4Frm.moveFormRow(jTable1, 1);
    }//GEN-LAST:event_RightActionPerformed

    /**
     * 修改保存按钮
     */
    private void AlterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AlterActionPerformed
        //在修改书本信息的时候需要选择具体一本书，但有可能会存在没有选择书本的情况
        if (Alter.getText().equals("修改")) {
            if (getbookno() == null) {
                return;
            }
            getdatatotextfiled();//修改后的数据显示在编辑框中
            Util4Frm.locktextfiled(InputBookNum);//锁定书籍编号这一栏
            jPanel1.setBorder(BorderFactory.createTitledBorder("编辑模式"));
            Alter.setText("保存");
            Reset.setEnabled(false);
            Delete.setEnabled(false);
            Add.setEnabled(false);
        } else {
            Msg msg = bookController.editbook(getbook());
            if (msg.getCode() == 200) {
                JOptionPane.showMessageDialog(null, msg.getMessage(), "系统提示", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, msg.getMessage(), "系统提示", JOptionPane.ERROR_MESSAGE);
            }
            resetTextfiled();//重置一下编辑框
            Util4Frm.unlocktextfiled(InputBookNum);
            RefreshBookInformation();
            //加入筛选模式
            jPanel1.setBorder(BorderFactory.createTitledBorder("筛选模式(左栏信息可筛选)"));
            Alter.setText("修改");
            Reset.setEnabled(true);
            Delete.setEnabled(true);
            Add.setEnabled(true);
        }
    }//GEN-LAST:event_AlterActionPerformed

    private void ResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResetActionPerformed
        // TODO add your handling code here:
        resetTextfiled();
        RefreshBookInformation();
    }//GEN-LAST:event_ResetActionPerformed
    /**
     * 移到顶部
     *
     * @param evt
     */
    private void FrontActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FrontActionPerformed
        Util4Frm.moveFormRowToTop(jTable1, 0);
    }//GEN-LAST:event_FrontActionPerformed
    /**
     * 移到底部
     *
     * @param evt
     */
    private void BackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackActionPerformed
        Util4Frm.moveFormRowToTop(jTable1, 1);
    }//GEN-LAST:event_BackActionPerformed

    private void jScrollPane2PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jScrollPane2PropertyChange
        // TODO add your handling code here:

    }//GEN-LAST:event_jScrollPane2PropertyChange

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseClicked

    private void InputPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputPriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InputPriceActionPerformed

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
            java.util.logging.Logger.getLogger(FrmBookManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmBookManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmBookManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmBookManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmBookManager().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Add;
    private javax.swing.JButton Alter;
    private javax.swing.JLabel Author;
    private javax.swing.JButton Back;
    private javax.swing.JLabel BookName;
    private javax.swing.JLabel BookNo;
    private javax.swing.JButton Delete;
    private javax.swing.JButton Front;
    private javax.swing.JTextField InputAuthor;
    private javax.swing.JTextField InputBookName;
    private javax.swing.JTextField InputBookNum;
    private javax.swing.JTextField InputPress;
    private javax.swing.JTextField InputPrice;
    private javax.swing.JTextField InputPublishdate;
    private javax.swing.JTextField InputShopNum;
    private javax.swing.JButton Left;
    private javax.swing.JLabel Press;
    private javax.swing.JLabel Price;
    private javax.swing.JLabel PublishDate;
    private javax.swing.JButton Refresh;
    private javax.swing.JButton Reset;
    private javax.swing.JButton Right;
    private javax.swing.JLabel StockInNum;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lblBack;
    // End of variables declaration//GEN-END:variables
}
