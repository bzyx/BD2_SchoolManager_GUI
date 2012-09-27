/********************************************************************************
** Form generated from reading ui file 'MainWindow.jui'
**
** Created: Cz 27. wrz 01:23:55 2012
**      by: Qt User Interface Compiler version 4.5.2
**
** WARNING! All changes made in this file will be lost when recompiling ui file!
********************************************************************************/

package pl.polsl.bd2.gui.forms;

import com.trolltech.qt.core.*;
import com.trolltech.qt.gui.*;

public class Ui_MainWindow implements com.trolltech.qt.QUiForm<QMainWindow>
{
    public QWidget centralwidget;
    public QGridLayout gridLayout_2;
    public QTabWidget tabWidget;
    public QWidget tab;
    public QGridLayout gridLayout;
    public QTableView tableDetailsData;
    public QTableView tableData;
    public QHBoxLayout horizontalLayout_3;
    public QLabel labelNameStudent;
    public QLabel labelNameEditStudent;
    public QLabel labelSurnameStudent;
    public QLabel labelSurnameEditStudent;
    public QLabel labelClassStudent;
    public QLabel labelClassEditStudent;
    public QVBoxLayout verticalLayout_5;
    public QSpacerItem verticalSpacer;
    public QLabel label;
    public QComboBox comboBoxStudent;
    public QSpacerItem verticalSpacer_4;
    public QLabel labelProgramInData;
    public QPushButton buttonToogleDetailsData;
    public QWidget tab_2;
    public QVBoxLayout verticalLayout_3;
    public QVBoxLayout verticalLayout_2;
    public QLabel label_7;
    public QTableView tableMessages;
    public QHBoxLayout horizontalLayout;
    public QGridLayout gridLayout_3;
    public QLabel label_4;
    public QLineEdit lineEditFromMessage;
    public QLabel label_5;
    public QLineEdit lineEditTopicMessage;
    public QLabel label_6;
    public QPlainTextEdit plainTextEditMessage;
    public QVBoxLayout verticalLayout;
    public QPushButton buttonNewMessage;
    public QPushButton buttonMarkAsRead;
    public QSpacerItem verticalSpacer_3;
    public QPushButton buttonDeleteMessage;
    public QPushButton buttonReplayMessage;
    public QSpacerItem verticalSpacer_2;
    public QWidget tab_3;
    public QVBoxLayout verticalLayout_8;
    public QVBoxLayout verticalLayout_6;
    public QLabel label_9;
    public QListView listContest;
    public QHBoxLayout horizontalLayout_2;
    public QPushButton buttonRegisterContest;
    public QSpacerItem horizontalSpacer_3;
    public QPushButton buttonEditContestType;
    public QSpacerItem horizontalSpacer;
    public QVBoxLayout verticalLayout_7;
    public QLabel label_8;
    public QPlainTextEdit plainTextEditAbouContest;
    public QHBoxLayout horizontalLayout_4;
    public QWidget tab_4;
    public QVBoxLayout verticalLayout_10;
    public QVBoxLayout verticalLayout_4;
    public QLabel label_10;
    public QListView listJustifications;
    public QVBoxLayout verticalLayout_9;
    public QLabel label_11;
    public QTableView tableAbsences;
    public QWidget tab_5;
    public QGridLayout gridLayout_4;
    public QTableView tableUsers;
    public QComboBox comboBoxObject;
    public QComboBox comboBoxClass;
    public QPushButton pushButtonAddRate;
    public QPushButton pushButtonAddNote;
    public QSpacerItem verticalSpacer_5;
    public QTableView tableDetailUsers;
    public QPushButton pushButtonEditRate;
    public QSpacerItem verticalSpacer_6;
    public QPushButton pushButtonJustify;
    public QPushButton pushButtonSave;
    public QWidget tab_6;
    public QGridLayout gridLayout_5;
    public QTableView tableViewPupils;
    public QPushButton pushButtonDeletePupil;
    public QGroupBox groupBoxAddPupil;
    public QGridLayout gridLayout_6;
    public QLabel label_2;
    public QLabel label_3;
    public QLabel label_12;
    public QLabel label_13;
    public QDialogButtonBox buttonBoxAddPupil;
    public QLabel label_14;
    public QLabel label_15;
    public QLabel label_16;
    public QLineEdit lineEditNewPupilName;
    public QLineEdit lineEditNewPupilVorname;
    public QLineEdit lineEditNewPupilStreet;
    public QLineEdit lineEditNewPupilCity;
    public QLineEdit lineEditNewPupilMail;
    public QLineEdit lineEditNewPupilLogin;
    public QLineEdit lineEditNewPupilPassword;
    public QSpacerItem verticalSpacer_7;
    public QGroupBox groupBoxAddClass;
    public QVBoxLayout verticalLayout_11;
    public QLineEdit lineEditNewClassName;
    public QDialogButtonBox buttonBoxAddClass;
    public QGridLayout gridLayout_7;
    public QPushButton pushButtonAddClass;
    public QComboBox comboBoxClassAll;
    public QPushButton pushButtonAddPupil;
    public QPushButton pushButtonMovePupil;
    public QSpacerItem horizontalSpacer_2;
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
        tabWidget.setMinimumSize(new QSize(0, 0));
        tabWidget.setMaximumSize(new QSize(16777215, 16777215));
        tab = new QWidget();
        tab.setObjectName("tab");
        gridLayout = new QGridLayout(tab);
        gridLayout.setObjectName("gridLayout");
        tableDetailsData = new QTableView(tab);
        tableDetailsData.setObjectName("tableDetailsData");
        tableDetailsData.setEnabled(true);
        tableDetailsData.setSelectionMode(com.trolltech.qt.gui.QAbstractItemView.SelectionMode.SingleSelection);
        tableDetailsData.setSelectionBehavior(com.trolltech.qt.gui.QAbstractItemView.SelectionBehavior.SelectRows);
        tableDetailsData.setSortingEnabled(false);

        gridLayout.addWidget(tableDetailsData, 2, 0, 1, 3);

        tableData = new QTableView(tab);
        tableData.setObjectName("tableData");
        tableData.setEditTriggers(com.trolltech.qt.gui.QAbstractItemView.EditTrigger.createQFlags(com.trolltech.qt.gui.QAbstractItemView.EditTrigger.AnyKeyPressed,com.trolltech.qt.gui.QAbstractItemView.EditTrigger.DoubleClicked,com.trolltech.qt.gui.QAbstractItemView.EditTrigger.EditKeyPressed));
        tableData.setAlternatingRowColors(false);
        tableData.setSelectionMode(com.trolltech.qt.gui.QAbstractItemView.SelectionMode.SingleSelection);
        tableData.setSelectionBehavior(com.trolltech.qt.gui.QAbstractItemView.SelectionBehavior.SelectRows);

        gridLayout.addWidget(tableData, 1, 0, 1, 1);

        horizontalLayout_3 = new QHBoxLayout();
        horizontalLayout_3.setObjectName("horizontalLayout_3");
        labelNameStudent = new QLabel(tab);
        labelNameStudent.setObjectName("labelNameStudent");

        horizontalLayout_3.addWidget(labelNameStudent);

        labelNameEditStudent = new QLabel(tab);
        labelNameEditStudent.setObjectName("labelNameEditStudent");

        horizontalLayout_3.addWidget(labelNameEditStudent);

        labelSurnameStudent = new QLabel(tab);
        labelSurnameStudent.setObjectName("labelSurnameStudent");

        horizontalLayout_3.addWidget(labelSurnameStudent);

        labelSurnameEditStudent = new QLabel(tab);
        labelSurnameEditStudent.setObjectName("labelSurnameEditStudent");

        horizontalLayout_3.addWidget(labelSurnameEditStudent);

        labelClassStudent = new QLabel(tab);
        labelClassStudent.setObjectName("labelClassStudent");

        horizontalLayout_3.addWidget(labelClassStudent);

        labelClassEditStudent = new QLabel(tab);
        labelClassEditStudent.setObjectName("labelClassEditStudent");

        horizontalLayout_3.addWidget(labelClassEditStudent);


        gridLayout.addLayout(horizontalLayout_3, 0, 0, 1, 1);

        verticalLayout_5 = new QVBoxLayout();
        verticalLayout_5.setObjectName("verticalLayout_5");
        verticalSpacer = new QSpacerItem(20, 40, com.trolltech.qt.gui.QSizePolicy.Policy.Minimum, com.trolltech.qt.gui.QSizePolicy.Policy.Expanding);

        verticalLayout_5.addItem(verticalSpacer);

        label = new QLabel(tab);
        label.setObjectName("label");

        verticalLayout_5.addWidget(label);

        comboBoxStudent = new QComboBox(tab);
        comboBoxStudent.setObjectName("comboBoxStudent");

        verticalLayout_5.addWidget(comboBoxStudent);

        verticalSpacer_4 = new QSpacerItem(20, 40, com.trolltech.qt.gui.QSizePolicy.Policy.Minimum, com.trolltech.qt.gui.QSizePolicy.Policy.Expanding);

        verticalLayout_5.addItem(verticalSpacer_4);

        labelProgramInData = new QLabel(tab);
        labelProgramInData.setObjectName("labelProgramInData");
        QSizePolicy sizePolicy = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Minimum, com.trolltech.qt.gui.QSizePolicy.Policy.Fixed);
        sizePolicy.setHorizontalStretch((byte)0);
        sizePolicy.setVerticalStretch((byte)0);
        sizePolicy.setHeightForWidth(labelProgramInData.sizePolicy().hasHeightForWidth());
        labelProgramInData.setSizePolicy(sizePolicy);
        labelProgramInData.setMinimumSize(new QSize(150, 0));
        labelProgramInData.setBaseSize(new QSize(0, 0));
        QFont font = new QFont();
        font.setPointSize(12);
        font.setBold(false);
        font.setUnderline(false);
        font.setWeight(50);
        labelProgramInData.setFont(font);
        labelProgramInData.setFrameShape(com.trolltech.qt.gui.QFrame.Shape.Box);
        labelProgramInData.setFrameShadow(com.trolltech.qt.gui.QFrame.Shadow.Plain);
        labelProgramInData.setLineWidth(1);
        labelProgramInData.setMidLineWidth(0);
        labelProgramInData.setAlignment(com.trolltech.qt.core.Qt.AlignmentFlag.createQFlags(com.trolltech.qt.core.Qt.AlignmentFlag.AlignCenter));

        verticalLayout_5.addWidget(labelProgramInData);

        buttonToogleDetailsData = new QPushButton(tab);
        buttonToogleDetailsData.setObjectName("buttonToogleDetailsData");

        verticalLayout_5.addWidget(buttonToogleDetailsData);


        gridLayout.addLayout(verticalLayout_5, 1, 2, 1, 1);

        tabWidget.addTab(tab, com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Date", null));
        tab_2 = new QWidget();
        tab_2.setObjectName("tab_2");
        verticalLayout_3 = new QVBoxLayout(tab_2);
        verticalLayout_3.setObjectName("verticalLayout_3");
        verticalLayout_2 = new QVBoxLayout();
        verticalLayout_2.setObjectName("verticalLayout_2");
        label_7 = new QLabel(tab_2);
        label_7.setObjectName("label_7");

        verticalLayout_2.addWidget(label_7);

        tableMessages = new QTableView(tab_2);
        tableMessages.setObjectName("tableMessages");
        tableMessages.setSelectionMode(com.trolltech.qt.gui.QAbstractItemView.SelectionMode.SingleSelection);
        tableMessages.setSelectionBehavior(com.trolltech.qt.gui.QAbstractItemView.SelectionBehavior.SelectRows);

        verticalLayout_2.addWidget(tableMessages);


        verticalLayout_3.addLayout(verticalLayout_2);

        horizontalLayout = new QHBoxLayout();
        horizontalLayout.setObjectName("horizontalLayout");
        gridLayout_3 = new QGridLayout();
        gridLayout_3.setObjectName("gridLayout_3");
        label_4 = new QLabel(tab_2);
        label_4.setObjectName("label_4");

        gridLayout_3.addWidget(label_4, 0, 0, 1, 1);

        lineEditFromMessage = new QLineEdit(tab_2);
        lineEditFromMessage.setObjectName("lineEditFromMessage");

        gridLayout_3.addWidget(lineEditFromMessage, 0, 1, 1, 1);

        label_5 = new QLabel(tab_2);
        label_5.setObjectName("label_5");

        gridLayout_3.addWidget(label_5, 1, 0, 1, 1);

        lineEditTopicMessage = new QLineEdit(tab_2);
        lineEditTopicMessage.setObjectName("lineEditTopicMessage");

        gridLayout_3.addWidget(lineEditTopicMessage, 1, 1, 1, 1);

        label_6 = new QLabel(tab_2);
        label_6.setObjectName("label_6");

        gridLayout_3.addWidget(label_6, 2, 0, 1, 2);

        plainTextEditMessage = new QPlainTextEdit(tab_2);
        plainTextEditMessage.setObjectName("plainTextEditMessage");

        gridLayout_3.addWidget(plainTextEditMessage, 3, 0, 1, 2);


        horizontalLayout.addLayout(gridLayout_3);

        verticalLayout = new QVBoxLayout();
        verticalLayout.setObjectName("verticalLayout");
        buttonNewMessage = new QPushButton(tab_2);
        buttonNewMessage.setObjectName("buttonNewMessage");

        verticalLayout.addWidget(buttonNewMessage);

        buttonMarkAsRead = new QPushButton(tab_2);
        buttonMarkAsRead.setObjectName("buttonMarkAsRead");

        verticalLayout.addWidget(buttonMarkAsRead);

        verticalSpacer_3 = new QSpacerItem(20, 64, com.trolltech.qt.gui.QSizePolicy.Policy.Minimum, com.trolltech.qt.gui.QSizePolicy.Policy.Minimum);

        verticalLayout.addItem(verticalSpacer_3);

        buttonDeleteMessage = new QPushButton(tab_2);
        buttonDeleteMessage.setObjectName("buttonDeleteMessage");

        verticalLayout.addWidget(buttonDeleteMessage);

        buttonReplayMessage = new QPushButton(tab_2);
        buttonReplayMessage.setObjectName("buttonReplayMessage");

        verticalLayout.addWidget(buttonReplayMessage);

        verticalSpacer_2 = new QSpacerItem(20, 40, com.trolltech.qt.gui.QSizePolicy.Policy.Minimum, com.trolltech.qt.gui.QSizePolicy.Policy.Expanding);

        verticalLayout.addItem(verticalSpacer_2);


        horizontalLayout.addLayout(verticalLayout);


        verticalLayout_3.addLayout(horizontalLayout);

        tabWidget.addTab(tab_2, com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Message", null));
        tab_3 = new QWidget();
        tab_3.setObjectName("tab_3");
        verticalLayout_8 = new QVBoxLayout(tab_3);
        verticalLayout_8.setObjectName("verticalLayout_8");
        verticalLayout_6 = new QVBoxLayout();
        verticalLayout_6.setObjectName("verticalLayout_6");
        label_9 = new QLabel(tab_3);
        label_9.setObjectName("label_9");

        verticalLayout_6.addWidget(label_9);

        listContest = new QListView(tab_3);
        listContest.setObjectName("listContest");

        verticalLayout_6.addWidget(listContest);

        horizontalLayout_2 = new QHBoxLayout();
        horizontalLayout_2.setObjectName("horizontalLayout_2");
        buttonRegisterContest = new QPushButton(tab_3);
        buttonRegisterContest.setObjectName("buttonRegisterContest");
        QSizePolicy sizePolicy1 = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Maximum, com.trolltech.qt.gui.QSizePolicy.Policy.Fixed);
        sizePolicy1.setHorizontalStretch((byte)0);
        sizePolicy1.setVerticalStretch((byte)0);
        sizePolicy1.setHeightForWidth(buttonRegisterContest.sizePolicy().hasHeightForWidth());
        buttonRegisterContest.setSizePolicy(sizePolicy1);

        horizontalLayout_2.addWidget(buttonRegisterContest);

        horizontalSpacer_3 = new QSpacerItem(40, 20, com.trolltech.qt.gui.QSizePolicy.Policy.Expanding, com.trolltech.qt.gui.QSizePolicy.Policy.Minimum);

        horizontalLayout_2.addItem(horizontalSpacer_3);

        buttonEditContestType = new QPushButton(tab_3);
        buttonEditContestType.setObjectName("buttonEditContestType");

        horizontalLayout_2.addWidget(buttonEditContestType);


        verticalLayout_6.addLayout(horizontalLayout_2);


        verticalLayout_8.addLayout(verticalLayout_6);

        horizontalSpacer = new QSpacerItem(20, 40, com.trolltech.qt.gui.QSizePolicy.Policy.Minimum, com.trolltech.qt.gui.QSizePolicy.Policy.Minimum);

        verticalLayout_8.addItem(horizontalSpacer);

        verticalLayout_7 = new QVBoxLayout();
        verticalLayout_7.setObjectName("verticalLayout_7");
        label_8 = new QLabel(tab_3);
        label_8.setObjectName("label_8");

        verticalLayout_7.addWidget(label_8);

        plainTextEditAbouContest = new QPlainTextEdit(tab_3);
        plainTextEditAbouContest.setObjectName("plainTextEditAbouContest");

        verticalLayout_7.addWidget(plainTextEditAbouContest);

        horizontalLayout_4 = new QHBoxLayout();
        horizontalLayout_4.setObjectName("horizontalLayout_4");

        verticalLayout_7.addLayout(horizontalLayout_4);


        verticalLayout_8.addLayout(verticalLayout_7);

        tabWidget.addTab(tab_3, com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Contest", null));
        tab_4 = new QWidget();
        tab_4.setObjectName("tab_4");
        verticalLayout_10 = new QVBoxLayout(tab_4);
        verticalLayout_10.setObjectName("verticalLayout_10");
        verticalLayout_4 = new QVBoxLayout();
        verticalLayout_4.setObjectName("verticalLayout_4");
        label_10 = new QLabel(tab_4);
        label_10.setObjectName("label_10");

        verticalLayout_4.addWidget(label_10);

        listJustifications = new QListView(tab_4);
        listJustifications.setObjectName("listJustifications");
        listJustifications.setSelectionBehavior(com.trolltech.qt.gui.QAbstractItemView.SelectionBehavior.SelectRows);

        verticalLayout_4.addWidget(listJustifications);


        verticalLayout_10.addLayout(verticalLayout_4);

        verticalLayout_9 = new QVBoxLayout();
        verticalLayout_9.setObjectName("verticalLayout_9");
        label_11 = new QLabel(tab_4);
        label_11.setObjectName("label_11");

        verticalLayout_9.addWidget(label_11);

        tableAbsences = new QTableView(tab_4);
        tableAbsences.setObjectName("tableAbsences");
        tableAbsences.setSelectionMode(com.trolltech.qt.gui.QAbstractItemView.SelectionMode.SingleSelection);
        tableAbsences.setSelectionBehavior(com.trolltech.qt.gui.QAbstractItemView.SelectionBehavior.SelectRows);

        verticalLayout_9.addWidget(tableAbsences);


        verticalLayout_10.addLayout(verticalLayout_9);

        tabWidget.addTab(tab_4, com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Justification && Absence", null));
        tab_5 = new QWidget();
        tab_5.setObjectName("tab_5");
        gridLayout_4 = new QGridLayout(tab_5);
        gridLayout_4.setObjectName("gridLayout_4");
        tableUsers = new QTableView(tab_5);
        tableUsers.setObjectName("tableUsers");
        QSizePolicy sizePolicy2 = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Expanding, com.trolltech.qt.gui.QSizePolicy.Policy.Expanding);
        sizePolicy2.setHorizontalStretch((byte)0);
        sizePolicy2.setVerticalStretch((byte)0);
        sizePolicy2.setHeightForWidth(tableUsers.sizePolicy().hasHeightForWidth());
        tableUsers.setSizePolicy(sizePolicy2);
        tableUsers.setSelectionMode(com.trolltech.qt.gui.QAbstractItemView.SelectionMode.SingleSelection);
        tableUsers.setSelectionBehavior(com.trolltech.qt.gui.QAbstractItemView.SelectionBehavior.SelectRows);

        gridLayout_4.addWidget(tableUsers, 0, 0, 7, 1);

        comboBoxObject = new QComboBox(tab_5);
        comboBoxObject.setObjectName("comboBoxObject");

        gridLayout_4.addWidget(comboBoxObject, 0, 1, 1, 1);

        comboBoxClass = new QComboBox(tab_5);
        comboBoxClass.setObjectName("comboBoxClass");

        gridLayout_4.addWidget(comboBoxClass, 1, 1, 1, 1);

        pushButtonAddRate = new QPushButton(tab_5);
        pushButtonAddRate.setObjectName("pushButtonAddRate");

        gridLayout_4.addWidget(pushButtonAddRate, 2, 1, 1, 1);

        pushButtonAddNote = new QPushButton(tab_5);
        pushButtonAddNote.setObjectName("pushButtonAddNote");

        gridLayout_4.addWidget(pushButtonAddNote, 4, 1, 1, 1);

        verticalSpacer_5 = new QSpacerItem(20, 40, com.trolltech.qt.gui.QSizePolicy.Policy.Minimum, com.trolltech.qt.gui.QSizePolicy.Policy.Expanding);

        gridLayout_4.addItem(verticalSpacer_5, 6, 1, 1, 1);

        tableDetailUsers = new QTableView(tab_5);
        tableDetailUsers.setObjectName("tableDetailUsers");
        tableDetailUsers.setSelectionMode(com.trolltech.qt.gui.QAbstractItemView.SelectionMode.SingleSelection);
        tableDetailUsers.setSelectionBehavior(com.trolltech.qt.gui.QAbstractItemView.SelectionBehavior.SelectRows);

        gridLayout_4.addWidget(tableDetailUsers, 7, 0, 3, 1);

        pushButtonEditRate = new QPushButton(tab_5);
        pushButtonEditRate.setObjectName("pushButtonEditRate");

        gridLayout_4.addWidget(pushButtonEditRate, 7, 1, 1, 1);

        verticalSpacer_6 = new QSpacerItem(20, 40, com.trolltech.qt.gui.QSizePolicy.Policy.Minimum, com.trolltech.qt.gui.QSizePolicy.Policy.Expanding);

        gridLayout_4.addItem(verticalSpacer_6, 9, 1, 1, 1);

        pushButtonJustify = new QPushButton(tab_5);
        pushButtonJustify.setObjectName("pushButtonJustify");

        gridLayout_4.addWidget(pushButtonJustify, 8, 1, 1, 1);

        pushButtonSave = new QPushButton(tab_5);
        pushButtonSave.setObjectName("pushButtonSave");

        gridLayout_4.addWidget(pushButtonSave, 5, 1, 1, 1);

        tabWidget.addTab(tab_5, com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Pupils", null));
        tab_6 = new QWidget();
        tab_6.setObjectName("tab_6");
        gridLayout_5 = new QGridLayout(tab_6);
        gridLayout_5.setSpacing(6);
        gridLayout_5.setObjectName("gridLayout_5");
        gridLayout_5.setSizeConstraint(com.trolltech.qt.gui.QLayout.SizeConstraint.SetDefaultConstraint);
        gridLayout_5.setContentsMargins(-1, 9, -1, -1);
        tableViewPupils = new QTableView(tab_6);
        tableViewPupils.setObjectName("tableViewPupils");
        QSizePolicy sizePolicy3 = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Expanding, com.trolltech.qt.gui.QSizePolicy.Policy.Expanding);
        sizePolicy3.setHorizontalStretch((byte)0);
        sizePolicy3.setVerticalStretch((byte)0);
        sizePolicy3.setHeightForWidth(tableViewPupils.sizePolicy().hasHeightForWidth());
        tableViewPupils.setSizePolicy(sizePolicy3);
        tableViewPupils.setSelectionMode(com.trolltech.qt.gui.QAbstractItemView.SelectionMode.SingleSelection);
        tableViewPupils.setSelectionBehavior(com.trolltech.qt.gui.QAbstractItemView.SelectionBehavior.SelectRows);

        gridLayout_5.addWidget(tableViewPupils, 4, 0, 1, 5);

        pushButtonDeletePupil = new QPushButton(tab_6);
        pushButtonDeletePupil.setObjectName("pushButtonDeletePupil");
        QSizePolicy sizePolicy4 = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Minimum, com.trolltech.qt.gui.QSizePolicy.Policy.Fixed);
        sizePolicy4.setHorizontalStretch((byte)0);
        sizePolicy4.setVerticalStretch((byte)0);
        sizePolicy4.setHeightForWidth(pushButtonDeletePupil.sizePolicy().hasHeightForWidth());
        pushButtonDeletePupil.setSizePolicy(sizePolicy4);

        gridLayout_5.addWidget(pushButtonDeletePupil, 5, 0, 1, 1);

        groupBoxAddPupil = new QGroupBox(tab_6);
        groupBoxAddPupil.setObjectName("groupBoxAddPupil");
        groupBoxAddPupil.setEnabled(true);
        gridLayout_6 = new QGridLayout(groupBoxAddPupil);
        gridLayout_6.setObjectName("gridLayout_6");
        label_2 = new QLabel(groupBoxAddPupil);
        label_2.setObjectName("label_2");

        gridLayout_6.addWidget(label_2, 0, 0, 1, 1);

        label_3 = new QLabel(groupBoxAddPupil);
        label_3.setObjectName("label_3");

        gridLayout_6.addWidget(label_3, 1, 0, 1, 1);

        label_12 = new QLabel(groupBoxAddPupil);
        label_12.setObjectName("label_12");

        gridLayout_6.addWidget(label_12, 2, 0, 1, 1);

        label_13 = new QLabel(groupBoxAddPupil);
        label_13.setObjectName("label_13");

        gridLayout_6.addWidget(label_13, 3, 0, 1, 1);

        buttonBoxAddPupil = new QDialogButtonBox(groupBoxAddPupil);
        buttonBoxAddPupil.setObjectName("buttonBoxAddPupil");
        buttonBoxAddPupil.setStandardButtons(com.trolltech.qt.gui.QDialogButtonBox.StandardButton.createQFlags(com.trolltech.qt.gui.QDialogButtonBox.StandardButton.Cancel,com.trolltech.qt.gui.QDialogButtonBox.StandardButton.Ok));

        gridLayout_6.addWidget(buttonBoxAddPupil, 7, 1, 1, 1);

        label_14 = new QLabel(groupBoxAddPupil);
        label_14.setObjectName("label_14");

        gridLayout_6.addWidget(label_14, 4, 0, 1, 1);

        label_15 = new QLabel(groupBoxAddPupil);
        label_15.setObjectName("label_15");

        gridLayout_6.addWidget(label_15, 5, 0, 1, 1);

        label_16 = new QLabel(groupBoxAddPupil);
        label_16.setObjectName("label_16");

        gridLayout_6.addWidget(label_16, 6, 0, 1, 1);

        lineEditNewPupilName = new QLineEdit(groupBoxAddPupil);
        lineEditNewPupilName.setObjectName("lineEditNewPupilName");

        gridLayout_6.addWidget(lineEditNewPupilName, 0, 1, 1, 1);

        lineEditNewPupilVorname = new QLineEdit(groupBoxAddPupil);
        lineEditNewPupilVorname.setObjectName("lineEditNewPupilVorname");

        gridLayout_6.addWidget(lineEditNewPupilVorname, 1, 1, 1, 1);

        lineEditNewPupilStreet = new QLineEdit(groupBoxAddPupil);
        lineEditNewPupilStreet.setObjectName("lineEditNewPupilStreet");

        gridLayout_6.addWidget(lineEditNewPupilStreet, 2, 1, 1, 1);

        lineEditNewPupilCity = new QLineEdit(groupBoxAddPupil);
        lineEditNewPupilCity.setObjectName("lineEditNewPupilCity");

        gridLayout_6.addWidget(lineEditNewPupilCity, 3, 1, 1, 1);

        lineEditNewPupilMail = new QLineEdit(groupBoxAddPupil);
        lineEditNewPupilMail.setObjectName("lineEditNewPupilMail");

        gridLayout_6.addWidget(lineEditNewPupilMail, 4, 1, 1, 1);

        lineEditNewPupilLogin = new QLineEdit(groupBoxAddPupil);
        lineEditNewPupilLogin.setObjectName("lineEditNewPupilLogin");

        gridLayout_6.addWidget(lineEditNewPupilLogin, 5, 1, 1, 1);

        lineEditNewPupilPassword = new QLineEdit(groupBoxAddPupil);
        lineEditNewPupilPassword.setObjectName("lineEditNewPupilPassword");

        gridLayout_6.addWidget(lineEditNewPupilPassword, 6, 1, 1, 1);

        verticalSpacer_7 = new QSpacerItem(20, 40, com.trolltech.qt.gui.QSizePolicy.Policy.Minimum, com.trolltech.qt.gui.QSizePolicy.Policy.Expanding);

        gridLayout_6.addItem(verticalSpacer_7, 8, 1, 1, 1);


        gridLayout_5.addWidget(groupBoxAddPupil, 1, 5, 4, 1);

        groupBoxAddClass = new QGroupBox(tab_6);
        groupBoxAddClass.setObjectName("groupBoxAddClass");
        groupBoxAddClass.setEnabled(true);
        groupBoxAddClass.setFlat(false);
        groupBoxAddClass.setCheckable(false);
        groupBoxAddClass.setChecked(false);
        verticalLayout_11 = new QVBoxLayout(groupBoxAddClass);
        verticalLayout_11.setObjectName("verticalLayout_11");
        lineEditNewClassName = new QLineEdit(groupBoxAddClass);
        lineEditNewClassName.setObjectName("lineEditNewClassName");

        verticalLayout_11.addWidget(lineEditNewClassName);

        buttonBoxAddClass = new QDialogButtonBox(groupBoxAddClass);
        buttonBoxAddClass.setObjectName("buttonBoxAddClass");
        buttonBoxAddClass.setStandardButtons(com.trolltech.qt.gui.QDialogButtonBox.StandardButton.createQFlags(com.trolltech.qt.gui.QDialogButtonBox.StandardButton.Cancel,com.trolltech.qt.gui.QDialogButtonBox.StandardButton.Ok));

        verticalLayout_11.addWidget(buttonBoxAddClass);


        gridLayout_5.addWidget(groupBoxAddClass, 0, 5, 1, 1);

        gridLayout_7 = new QGridLayout();
        gridLayout_7.setObjectName("gridLayout_7");
        pushButtonAddClass = new QPushButton(tab_6);
        pushButtonAddClass.setObjectName("pushButtonAddClass");
        QSizePolicy sizePolicy5 = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Minimum, com.trolltech.qt.gui.QSizePolicy.Policy.Fixed);
        sizePolicy5.setHorizontalStretch((byte)0);
        sizePolicy5.setVerticalStretch((byte)0);
        sizePolicy5.setHeightForWidth(pushButtonAddClass.sizePolicy().hasHeightForWidth());
        pushButtonAddClass.setSizePolicy(sizePolicy5);
        pushButtonAddClass.setMinimumSize(new QSize(150, 0));
        pushButtonAddClass.setMaximumSize(new QSize(16777215, 16777215));

        gridLayout_7.addWidget(pushButtonAddClass, 0, 0, 1, 1);

        comboBoxClassAll = new QComboBox(tab_6);
        comboBoxClassAll.setObjectName("comboBoxClassAll");
        QSizePolicy sizePolicy6 = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Minimum, com.trolltech.qt.gui.QSizePolicy.Policy.Fixed);
        sizePolicy6.setHorizontalStretch((byte)0);
        sizePolicy6.setVerticalStretch((byte)0);
        sizePolicy6.setHeightForWidth(comboBoxClassAll.sizePolicy().hasHeightForWidth());
        comboBoxClassAll.setSizePolicy(sizePolicy6);
        comboBoxClassAll.setMinimumSize(new QSize(150, 0));
        comboBoxClassAll.setMaximumSize(new QSize(16777215, 16777215));

        gridLayout_7.addWidget(comboBoxClassAll, 0, 1, 1, 1);

        pushButtonAddPupil = new QPushButton(tab_6);
        pushButtonAddPupil.setObjectName("pushButtonAddPupil");
        QSizePolicy sizePolicy7 = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Preferred, com.trolltech.qt.gui.QSizePolicy.Policy.Fixed);
        sizePolicy7.setHorizontalStretch((byte)0);
        sizePolicy7.setVerticalStretch((byte)0);
        sizePolicy7.setHeightForWidth(pushButtonAddPupil.sizePolicy().hasHeightForWidth());
        pushButtonAddPupil.setSizePolicy(sizePolicy7);
        pushButtonAddPupil.setMaximumSize(new QSize(16777215, 16777215));

        gridLayout_7.addWidget(pushButtonAddPupil, 1, 0, 1, 1);

        pushButtonMovePupil = new QPushButton(tab_6);
        pushButtonMovePupil.setObjectName("pushButtonMovePupil");
        QSizePolicy sizePolicy8 = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Minimum, com.trolltech.qt.gui.QSizePolicy.Policy.Fixed);
        sizePolicy8.setHorizontalStretch((byte)0);
        sizePolicy8.setVerticalStretch((byte)0);
        sizePolicy8.setHeightForWidth(pushButtonMovePupil.sizePolicy().hasHeightForWidth());
        pushButtonMovePupil.setSizePolicy(sizePolicy8);
        pushButtonMovePupil.setMinimumSize(new QSize(0, 0));
        pushButtonMovePupil.setMaximumSize(new QSize(16777215, 16777215));

        gridLayout_7.addWidget(pushButtonMovePupil, 1, 1, 1, 1);


        gridLayout_5.addLayout(gridLayout_7, 0, 0, 1, 2);

        horizontalSpacer_2 = new QSpacerItem(40, 20, com.trolltech.qt.gui.QSizePolicy.Policy.Expanding, com.trolltech.qt.gui.QSizePolicy.Policy.Minimum);

        gridLayout_5.addItem(horizontalSpacer_2, 0, 2, 1, 1);

        tabWidget.addTab(tab_6, com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Class menagment", null));

        gridLayout_2.addWidget(tabWidget, 0, 1, 1, 1);

        MainWindow.setCentralWidget(centralwidget);
        menubar = new QMenuBar(MainWindow);
        menubar.setObjectName("menubar");
        menubar.setGeometry(new QRect(0, 0, 800, 22));
        MainWindow.setMenuBar(menubar);
        statusbar = new QStatusBar(MainWindow);
        statusbar.setObjectName("statusbar");
        MainWindow.setStatusBar(statusbar);
        QWidget.setTabOrder(comboBoxStudent, tableDetailsData);
        retranslateUi(MainWindow);

        tabWidget.setCurrentIndex(4);


        MainWindow.connectSlotsByName();
    } // setupUi

    void retranslateUi(QMainWindow MainWindow)
    {
        MainWindow.setWindowTitle(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "School Helper 2012 Small Demo Edition", null));
        tableDetailsData.setStyleSheet(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "QTableView{\n"+
"	show-decoration-selected: 1;\n"+
"}\n"+
"QTableView::item {\n"+
"	padding-bottom: 0px;\n"+
"	padding-top: 0px;\n"+
"	padding-left: 20px;\n"+
"	padding-right: 20px;\n"+
"	margin: 0px;\n"+
"}", null));
        tableData.setStyleSheet(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "QTableView{\n"+
"	show-decoration-selected: 1;\n"+
"}\n"+
"QTableView::item {\n"+
"	padding-bottom: 0px;\n"+
"	padding-top: 0px;\n"+
"	padding-left: 20px;\n"+
"	padding-right: 20px;\n"+
"	margin: 0px;\n"+
"}", null));
        labelNameStudent.setText(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Name:", null));
        labelNameEditStudent.setText(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "xxx", null));
        labelSurnameStudent.setText(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Surname:", null));
        labelSurnameEditStudent.setText(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "xxx", null));
        labelClassStudent.setText(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Class:", null));
        labelClassEditStudent.setText(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "xxx", null));
        label.setText(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Student:", null));
        labelProgramInData.setText(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Subject", null));
        buttonToogleDetailsData.setText(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Toogle details", null));
        tabWidget.setTabText(tabWidget.indexOf(tab), com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Date", null));
        label_7.setText(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Messages:", null));
        label_4.setText(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "From: ", null));
        label_5.setText(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Topic: ", null));
        label_6.setText(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Message text:", null));
        buttonNewMessage.setText(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "New message", null));
        buttonMarkAsRead.setText(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Mark as read", null));
        buttonDeleteMessage.setText(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Delete", null));
        buttonReplayMessage.setText(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Replay", null));
        tabWidget.setTabText(tabWidget.indexOf(tab_2), com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Message", null));
        label_9.setText(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Konkursy:", null));
        buttonRegisterContest.setText(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Dodaj konkurs", null));
        buttonEditContestType.setText(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Edytuj typy konkursu", null));
        label_8.setText(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "O konkursie", null));
        tabWidget.setTabText(tabWidget.indexOf(tab_3), com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Contest", null));
        label_10.setText(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Justifications:", null));
        label_11.setText(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Absences", null));
        tabWidget.setTabText(tabWidget.indexOf(tab_4), com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Justification && Absence", null));
        pushButtonAddRate.setText(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Add rate", null));
        pushButtonAddNote.setText(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Add note", null));
        pushButtonEditRate.setText(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Edit rate", null));
        pushButtonJustify.setText(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Justify", null));
        pushButtonSave.setText(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Save", null));
        tabWidget.setTabText(tabWidget.indexOf(tab_5), com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Pupils", null));
        pushButtonDeletePupil.setText(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Delete pupil", null));
        groupBoxAddPupil.setTitle(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Personal data", null));
        label_2.setText(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Name: ", null));
        label_3.setText(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Vorname: ", null));
        label_12.setText(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Streat: ", null));
        label_13.setText(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "City: ", null));
        label_14.setText(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Mail: ", null));
        label_15.setText(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Login: ", null));
        label_16.setText(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Password: ", null));
        groupBoxAddClass.setTitle(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Class name", null));
        pushButtonAddClass.setText(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Add class", null));
        pushButtonAddPupil.setText(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Add pupil", null));
        pushButtonMovePupil.setText(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Move pupil", null));
        tabWidget.setTabText(tabWidget.indexOf(tab_6), com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Class menagment", null));
    } // retranslateUi

}

