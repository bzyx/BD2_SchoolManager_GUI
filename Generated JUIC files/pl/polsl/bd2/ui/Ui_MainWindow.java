/********************************************************************************
** Form generated from reading ui file 'MainWindow.jui'
**
** Created: Œr 11. kwi 15:36:40 2012
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
    public QGridLayout gridLayout;
    public QLabel label;
    public QPushButton pushButton;
    public QSpacerItem verticalSpacer;
    public QMenuBar menubar;
    public QStatusBar statusbar;

    public Ui_MainWindow() { super(); }

    public void setupUi(QMainWindow MainWindow)
    {
        MainWindow.setObjectName("MainWindow");
        MainWindow.resize(new QSize(800, 600).expandedTo(MainWindow.minimumSizeHint()));
        centralwidget = new QWidget(MainWindow);
        centralwidget.setObjectName("centralwidget");
        gridLayout = new QGridLayout(centralwidget);
        gridLayout.setObjectName("gridLayout");
        label = new QLabel(centralwidget);
        label.setObjectName("label");

        gridLayout.addWidget(label, 0, 0, 1, 1);

        pushButton = new QPushButton(centralwidget);
        pushButton.setObjectName("pushButton");

        gridLayout.addWidget(pushButton, 0, 1, 1, 1);

        verticalSpacer = new QSpacerItem(20, 40, com.trolltech.qt.gui.QSizePolicy.Policy.Minimum, com.trolltech.qt.gui.QSizePolicy.Policy.Expanding);

        gridLayout.addItem(verticalSpacer, 1, 0, 1, 1);

        MainWindow.setCentralWidget(centralwidget);
        menubar = new QMenuBar(MainWindow);
        menubar.setObjectName("menubar");
        menubar.setGeometry(new QRect(0, 0, 800, 19));
        MainWindow.setMenuBar(menubar);
        statusbar = new QStatusBar(MainWindow);
        statusbar.setObjectName("statusbar");
        MainWindow.setStatusBar(statusbar);
        retranslateUi(MainWindow);

        MainWindow.connectSlotsByName();
    } // setupUi

    void retranslateUi(QMainWindow MainWindow)
    {
        MainWindow.setWindowTitle(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "School Helper 2012 Small Demo Edition", null));
        label.setText(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Hello world!", null));
        pushButton.setText(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Push to ContactForm", null));
    } // retranslateUi

}

