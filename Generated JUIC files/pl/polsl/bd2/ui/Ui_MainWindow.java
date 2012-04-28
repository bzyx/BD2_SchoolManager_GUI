/********************************************************************************
** Form generated from reading ui file 'MainWindow.jui'
**
** Created: niedz. kwi 29 00:20:09 2012
**      by: Qt User Interface Compiler version 4.5.2
**
** WARNING! All changes made in this file will be lost when recompiling ui file!
********************************************************************************/

package pl.polsl.bd2.ui;

import com.trolltech.qt.core.*;
import com.trolltech.qt.gui.*;

public class Ui_MainWindow implements com.trolltech.qt.QUiForm<QMainWindow>
{
    public QWidget centralwidget;
    public QGridLayout gridLayout_2;
    public QTabWidget tabWidget;
    public QWidget tab;
    public QGridLayout gridLayout;
    public QTableView tableView;
    public QPushButton pushButton_2;
    public QComboBox comboBox;
    public QTableView tableView_2;
    public QLabel label_2;
    public QLabel label_3;
    public QWidget tab_2;
    public QWidget tab_3;
    public QWidget tab_4;
    public QLabel label;
    public QSpacerItem verticalSpacer;
    public QPushButton pushButton;
    public QMenuBar menubar;
    public QStatusBar statusbar;

    public Ui_MainWindow() { super(); }

    public void setupUi(QMainWindow MainWindow)
    {
        MainWindow.setObjectName("MainWindow");
        MainWindow.resize(new QSize(800, 600).expandedTo(MainWindow.minimumSizeHint()));
        centralwidget = new QWidget(MainWindow);
        centralwidget.setObjectName("centralwidget");
        gridLayout_2 = new QGridLayout(centralwidget);
        gridLayout_2.setObjectName("gridLayout_2");
        tabWidget = new QTabWidget(centralwidget);
        tabWidget.setObjectName("tabWidget");
        tab = new QWidget();
        tab.setObjectName("tab");
        gridLayout = new QGridLayout(tab);
        gridLayout.setObjectName("gridLayout");
        tableView = new QTableView(tab);
        tableView.setObjectName("tableView");

        gridLayout.addWidget(tableView, 1, 0, 5, 1);

        pushButton_2 = new QPushButton(tab);
        pushButton_2.setObjectName("pushButton_2");

        gridLayout.addWidget(pushButton_2, 5, 1, 1, 1);

        comboBox = new QComboBox(tab);
        comboBox.setObjectName("comboBox");

        gridLayout.addWidget(comboBox, 1, 1, 1, 1);

        tableView_2 = new QTableView(tab);
        tableView_2.setObjectName("tableView_2");

        gridLayout.addWidget(tableView_2, 6, 0, 1, 2);

        label_2 = new QLabel(tab);
        label_2.setObjectName("label_2");
        QFont font = new QFont();
        font.setPointSize(12);
        label_2.setFont(font);

        gridLayout.addWidget(label_2, 0, 0, 1, 2);

        label_3 = new QLabel(tab);
        label_3.setObjectName("label_3");
        QSizePolicy sizePolicy = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Minimum, com.trolltech.qt.gui.QSizePolicy.Policy.Fixed);
        sizePolicy.setHorizontalStretch((byte)0);
        sizePolicy.setVerticalStretch((byte)0);
        sizePolicy.setHeightForWidth(label_3.sizePolicy().hasHeightForWidth());
        label_3.setSizePolicy(sizePolicy);
        label_3.setMinimumSize(new QSize(150, 0));
        label_3.setBaseSize(new QSize(0, 0));
        QFont font1 = new QFont();
        font1.setPointSize(12);
        font1.setBold(false);
        font1.setUnderline(false);
        font1.setWeight(50);
        label_3.setFont(font1);
        label_3.setFrameShape(com.trolltech.qt.gui.QFrame.Shape.Box);
        label_3.setFrameShadow(com.trolltech.qt.gui.QFrame.Shadow.Plain);
        label_3.setLineWidth(1);
        label_3.setMidLineWidth(0);
        label_3.setAlignment(com.trolltech.qt.core.Qt.AlignmentFlag.createQFlags(com.trolltech.qt.core.Qt.AlignmentFlag.AlignCenter));

        gridLayout.addWidget(label_3, 2, 1, 1, 1);

        tabWidget.addTab(tab, com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Date", null));
        tab_2 = new QWidget();
        tab_2.setObjectName("tab_2");
        tabWidget.addTab(tab_2, com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Message", null));
        tab_3 = new QWidget();
        tab_3.setObjectName("tab_3");
        tabWidget.addTab(tab_3, com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Contest", null));
        tab_4 = new QWidget();
        tab_4.setObjectName("tab_4");
        tabWidget.addTab(tab_4, com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Justification", null));

        gridLayout_2.addWidget(tabWidget, 0, 0, 1, 2);

        label = new QLabel(centralwidget);
        label.setObjectName("label");

        gridLayout_2.addWidget(label, 2, 0, 1, 1);

        verticalSpacer = new QSpacerItem(20, 40, com.trolltech.qt.gui.QSizePolicy.Policy.Minimum, com.trolltech.qt.gui.QSizePolicy.Policy.Expanding);

        gridLayout_2.addItem(verticalSpacer, 3, 1, 1, 1);

        pushButton = new QPushButton(centralwidget);
        pushButton.setObjectName("pushButton");

        gridLayout_2.addWidget(pushButton, 2, 1, 1, 1);

        MainWindow.setCentralWidget(centralwidget);
        menubar = new QMenuBar(MainWindow);
        menubar.setObjectName("menubar");
        menubar.setGeometry(new QRect(0, 0, 800, 22));
        MainWindow.setMenuBar(menubar);
        statusbar = new QStatusBar(MainWindow);
        statusbar.setObjectName("statusbar");
        MainWindow.setStatusBar(statusbar);
        QWidget.setTabOrder(tabWidget, tableView);
        QWidget.setTabOrder(tableView, comboBox);
        QWidget.setTabOrder(comboBox, pushButton_2);
        QWidget.setTabOrder(pushButton_2, tableView_2);
        QWidget.setTabOrder(tableView_2, pushButton);
        retranslateUi(MainWindow);

        tabWidget.setCurrentIndex(0);


        MainWindow.connectSlotsByName();
    } // setupUi

    void retranslateUi(QMainWindow MainWindow)
    {
        MainWindow.setWindowTitle(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "School Helper 2012 Small Demo Edition", null));
        pushButton_2.setText(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Toogle details", null));
        label_2.setText(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Name: xxx; Forname: yyy; Age: zz; Class: aa", null));
        label_3.setText(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Object", null));
        tabWidget.setTabText(tabWidget.indexOf(tab), com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Date", null));
        tabWidget.setTabText(tabWidget.indexOf(tab_2), com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Message", null));
        tabWidget.setTabText(tabWidget.indexOf(tab_3), com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Contest", null));
        tabWidget.setTabText(tabWidget.indexOf(tab_4), com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Justification", null));
        label.setText(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Hello world!", null));
        pushButton.setText(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Push to ContactForm", null));
    } // retranslateUi

}

