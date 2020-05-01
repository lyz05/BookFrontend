/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.home999.bookfrontend.utils;

import java.awt.Component;
import javax.swing.*;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author congcong
 */
public class Util4Frm {

    private Util4Frm() {
    }       //禁止实例化
    /**
     * 当前登陆用户的ReaderNO，对所有的窗口均适用
     */
    public static String readerNO = "R2006001";
    /**
     * 排序的方向，用于对jTable中的列排序
     */
    private static boolean sort = false;

    /**
     * 设置窗口显示效果，原界面丑拒
     *
     * @param comp 提供界面对应的Component
     */
    public static void setUI(Component comp) {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            SwingUtilities.updateComponentTreeUI(comp);
        } catch (Exception err) {
            System.out.println("设置窗口效果异常:" + err);
        }
    }

    /**
     * 根据表格内容自动调整JTable列宽度
     *
     * @param table 修改的jtable
     */
    public static void resizeColumnWidth(JTable table) {
        final TableColumnModel columnModel = table.getColumnModel();
        for (int column = 0; column < table.getColumnCount(); column++) {
            int width = 15; // Min width
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer renderer = table.getCellRenderer(row, column);
                Component comp = table.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width + 1, width);
            }
            if (width > 300) {
                width = 300;
            }
            columnModel.getColumn(column).setPreferredWidth(width);
        }
    }

    /**
     * 移动jTable当前选中行的光标
     *
     * @param jtable 要移动光标的jTable
     * @param dis 位置相较于原来的位移，+1表示向下，-1表示向上
     */
    public static void moveFormRow(JTable jtable, int dis) {
        int tmp = jtable.getSelectedRow() + dis;
        if (tmp < 0) {
            JOptionPane.showMessageDialog(null, "已经是第一条数据了", "系统提示", JOptionPane.INFORMATION_MESSAGE);
        } else if (tmp >= jtable.getRowCount()) {
            JOptionPane.showMessageDialog(null, "已经是最后一条数据了", "系统提示", JOptionPane.INFORMATION_MESSAGE);
        } else {
            jtable.setRowSelectionInterval(tmp, tmp);
        }
    }

    /**
     * 移动jTable当前选中行光标到顶部或底部
     *
     * @param jtable
     * @param status
     */
    public static void moveFormRowToTop(JTable jtable, int status) {
        int tmp = 0;
        if (status == 1) {
            tmp = jtable.getRowCount() - 1;
        }
        jtable.setRowSelectionInterval(tmp, tmp);
    }

    /**
     * 刷新底部状态栏的标签显示
     *
     * @param jtable 待处理的表格
     * @param jlabel 底部状态栏标签
     */
    public static void resetBackText(JTable jtable, JLabel jlabel) {
        int row = jtable.getSelectedRow();
        int tot = jtable.getRowCount();
        if (row >= 0 && row < tot) {
            jlabel.setText("这是第" + (row + 1) + "条记录，共查询到" + tot + "条记录");
        } else {
            jlabel.setText("共查询到" + tot + "条记录");
        }
    }

    /**
     * 锁定控件
     *
     * @param jtextfield 待锁定控件
     */
    public static void locktextfiled(JTextField jtextfield) {
        jtextfield.setEnabled(false);
    }

    /**
     * 解锁控件
     *
     * @param jtextfield 待解锁空间
     */
    public static void unlocktextfiled(JTextField jtextfield) {
        jtextfield.setEnabled(true);
    }

    /**
     * 确认删除对话框
     *
     * @return 是否按下确认按钮
     */
    public static boolean confirmdelete() {
        return JOptionPane.showConfirmDialog(null, "你确认删除该条数据吗？", "系统提示", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION;
    }

    /**
     * 确认重置密码对话框
     *
     * @return 是否按下确认按钮
     */
    public static boolean confirmresetpwd() {
        return JOptionPane.showConfirmDialog(null, "你确认重置该用户密码吗？", "系统提示", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION;
    }

}
