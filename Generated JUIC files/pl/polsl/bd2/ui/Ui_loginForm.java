/********************************************************************************
** Form generated from reading ui file 'loginForm.jui'
**
** Created: So 28. kwi 19:51:56 2012
**      by: Qt User Interface Compiler version 4.5.2
**
** WARNING! All changes made in this file will be lost when recompiling ui file!
********************************************************************************/

package pl.polsl.bd2.ui;

import com.trolltech.qt.core.*;
import com.trolltech.qt.gui.*;

public class Ui_loginForm implements com.trolltech.qt.QUiForm<QWidget>
{
    public QVBoxLayout verticalLayout;
    public QLabel label_3;
    public QSpacerItem verticalSpacer_2;
    public QGridLayout gridLayout;
    public QLabel label;
    public QLineEdit lineEdit;
    public QLabel label_2;
    public QLineEdit lineEdit_2;
    public QDialogButtonBox buttonBox;

    public Ui_loginForm() { super(); }

    public void setupUi(QWidget loginForm)
    {
        loginForm.setObjectName("loginForm");
        loginForm.setWindowModality(com.trolltech.qt.core.Qt.WindowModality.ApplicationModal);
        loginForm.resize(new QSize(330, 200).expandedTo(loginForm.minimumSizeHint()));
        QSizePolicy sizePolicy = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Fixed, com.trolltech.qt.gui.QSizePolicy.Policy.Fixed);
        sizePolicy.setHorizontalStretch((byte)0);
        sizePolicy.setVerticalStretch((byte)0);
        sizePolicy.setHeightForWidth(loginForm.sizePolicy().hasHeightForWidth());
        loginForm.setSizePolicy(sizePolicy);
        loginForm.setMinimumSize(new QSize(330, 200));
        loginForm.setMaximumSize(new QSize(330, 200));
        loginForm.setContextMenuPolicy(com.trolltech.qt.core.Qt.ContextMenuPolicy.DefaultContextMenu);
        loginForm.setAcceptDrops(false);
        verticalLayout = new QVBoxLayout(loginForm);
        verticalLayout.setObjectName("verticalLayout");
        label_3 = new QLabel(loginForm);
        label_3.setObjectName("label_3");
        QFont font = new QFont();
        font.setPointSize(13);
        label_3.setFont(font);
        label_3.setAlignment(com.trolltech.qt.core.Qt.AlignmentFlag.createQFlags(com.trolltech.qt.core.Qt.AlignmentFlag.AlignCenter));

        verticalLayout.addWidget(label_3);

        verticalSpacer_2 = new QSpacerItem(20, 40, com.trolltech.qt.gui.QSizePolicy.Policy.Minimum, com.trolltech.qt.gui.QSizePolicy.Policy.Expanding);

        verticalLayout.addItem(verticalSpacer_2);

        gridLayout = new QGridLayout();
        gridLayout.setObjectName("gridLayout");
        gridLayout.setSizeConstraint(com.trolltech.qt.gui.QLayout.SizeConstraint.SetDefaultConstraint);
        label = new QLabel(loginForm);
        label.setObjectName("label");
        QSizePolicy sizePolicy1 = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Preferred, com.trolltech.qt.gui.QSizePolicy.Policy.Preferred);
        sizePolicy1.setHorizontalStretch((byte)0);
        sizePolicy1.setVerticalStretch((byte)0);
        sizePolicy1.setHeightForWidth(label.sizePolicy().hasHeightForWidth());
        label.setSizePolicy(sizePolicy1);
        QFont font1 = new QFont();
        font1.setPointSize(12);
        label.setFont(font1);
        label.setContextMenuPolicy(com.trolltech.qt.core.Qt.ContextMenuPolicy.DefaultContextMenu);
        label.setLayoutDirection(com.trolltech.qt.core.Qt.LayoutDirection.LeftToRight);
        label.setAutoFillBackground(false);
        label.setLineWidth(1);
        label.setTextFormat(com.trolltech.qt.core.Qt.TextFormat.AutoText);
        label.setAlignment(com.trolltech.qt.core.Qt.AlignmentFlag.createQFlags(com.trolltech.qt.core.Qt.AlignmentFlag.AlignRight,com.trolltech.qt.core.Qt.AlignmentFlag.AlignVCenter));

        gridLayout.addWidget(label, 0, 0, 1, 1);

        lineEdit = new QLineEdit(loginForm);
        lineEdit.setObjectName("lineEdit");
        QSizePolicy sizePolicy2 = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Preferred, com.trolltech.qt.gui.QSizePolicy.Policy.Preferred);
        sizePolicy2.setHorizontalStretch((byte)0);
        sizePolicy2.setVerticalStretch((byte)0);
        sizePolicy2.setHeightForWidth(lineEdit.sizePolicy().hasHeightForWidth());
        lineEdit.setSizePolicy(sizePolicy2);
        QFont font2 = new QFont();
        font2.setPointSize(12);
        lineEdit.setFont(font2);

        gridLayout.addWidget(lineEdit, 0, 1, 1, 1);

        label_2 = new QLabel(loginForm);
        label_2.setObjectName("label_2");
        QFont font3 = new QFont();
        font3.setPointSize(12);
        label_2.setFont(font3);
        label_2.setFrameShape(com.trolltech.qt.gui.QFrame.Shape.NoFrame);
        label_2.setAlignment(com.trolltech.qt.core.Qt.AlignmentFlag.createQFlags(com.trolltech.qt.core.Qt.AlignmentFlag.AlignRight,com.trolltech.qt.core.Qt.AlignmentFlag.AlignVCenter));

        gridLayout.addWidget(label_2, 1, 0, 1, 1);

        lineEdit_2 = new QLineEdit(loginForm);
        lineEdit_2.setObjectName("lineEdit_2");
        QFont font4 = new QFont();
        font4.setPointSize(12);
        lineEdit_2.setFont(font4);
        lineEdit_2.setEchoMode(com.trolltech.qt.gui.QLineEdit.EchoMode.Password);

        gridLayout.addWidget(lineEdit_2, 1, 1, 1, 1);


        verticalLayout.addLayout(gridLayout);

        buttonBox = new QDialogButtonBox(loginForm);
        buttonBox.setObjectName("buttonBox");
        buttonBox.setStandardButtons(com.trolltech.qt.gui.QDialogButtonBox.StandardButton.createQFlags(com.trolltech.qt.gui.QDialogButtonBox.StandardButton.Close,com.trolltech.qt.gui.QDialogButtonBox.StandardButton.Ok));
        buttonBox.setCenterButtons(true);

        verticalLayout.addWidget(buttonBox);

        QWidget.setTabOrder(lineEdit, lineEdit_2);
        QWidget.setTabOrder(lineEdit_2, buttonBox);
        retranslateUi(loginForm);

        loginForm.connectSlotsByName();
    } // setupUi

    void retranslateUi(QWidget loginForm)
    {
        loginForm.setWindowTitle(com.trolltech.qt.core.QCoreApplication.translate("loginForm", "Login Form", null));
        label_3.setText(com.trolltech.qt.core.QCoreApplication.translate("loginForm", "Enter login and password to login.", null));
        label.setText(com.trolltech.qt.core.QCoreApplication.translate("loginForm", "Login:  ", null));
        label_2.setText(com.trolltech.qt.core.QCoreApplication.translate("loginForm", "Password: ", null));
    } // retranslateUi

}

