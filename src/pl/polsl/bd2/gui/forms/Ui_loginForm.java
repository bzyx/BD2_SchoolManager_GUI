/********************************************************************************
** Form generated from reading ui file 'LoginForm.jui'
**
** Created: Œr 19. wrz 23:23:09 2012
**      by: Qt User Interface Compiler version 4.5.2
**
** WARNING! All changes made in this file will be lost when recompiling ui file!
********************************************************************************/

package pl.polsl.bd2.gui.forms;

import com.trolltech.qt.core.*;
import com.trolltech.qt.gui.*;

public class Ui_loginForm implements com.trolltech.qt.QUiForm<QWidget>
{
    public QGridLayout gridLayout_2;
    public QLabel label_3;
    public QSpacerItem horizontalSpacer;
    public QDialogButtonBox buttonBox;
    public QGridLayout gridLayout;
    public QLabel label;
    public QLineEdit lineEdit;
    public QLabel label_2;
    public QLineEdit lineEdit_2;

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
        gridLayout_2 = new QGridLayout(loginForm);
        gridLayout_2.setObjectName("gridLayout_2");
        label_3 = new QLabel(loginForm);
        label_3.setObjectName("label_3");
        QFont font = new QFont();
        font.setPointSize(13);
        font.setBold(true);
        font.setWeight(75);
        label_3.setFont(font);
        label_3.setCursor(new QCursor(Qt.CursorShape.BlankCursor));
        label_3.setFrameShape(com.trolltech.qt.gui.QFrame.Shape.Box);
        label_3.setFrameShadow(com.trolltech.qt.gui.QFrame.Shadow.Sunken);
        label_3.setAlignment(com.trolltech.qt.core.Qt.AlignmentFlag.createQFlags(com.trolltech.qt.core.Qt.AlignmentFlag.AlignCenter));

        gridLayout_2.addWidget(label_3, 0, 0, 1, 2);

        horizontalSpacer = new QSpacerItem(147, 21, com.trolltech.qt.gui.QSizePolicy.Policy.Expanding, com.trolltech.qt.gui.QSizePolicy.Policy.Minimum);

        gridLayout_2.addItem(horizontalSpacer, 4, 0, 1, 1);

        buttonBox = new QDialogButtonBox(loginForm);
        buttonBox.setObjectName("buttonBox");
        buttonBox.setStandardButtons(com.trolltech.qt.gui.QDialogButtonBox.StandardButton.createQFlags(com.trolltech.qt.gui.QDialogButtonBox.StandardButton.Cancel,com.trolltech.qt.gui.QDialogButtonBox.StandardButton.Ok));
        buttonBox.setCenterButtons(false);

        gridLayout_2.addWidget(buttonBox, 4, 1, 1, 1);

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
        font1.setPointSize(8);
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
        QSizePolicy sizePolicy2 = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Expanding, com.trolltech.qt.gui.QSizePolicy.Policy.Fixed);
        sizePolicy2.setHorizontalStretch((byte)0);
        sizePolicy2.setVerticalStretch((byte)0);
        sizePolicy2.setHeightForWidth(lineEdit.sizePolicy().hasHeightForWidth());
        lineEdit.setSizePolicy(sizePolicy2);
        QFont font2 = new QFont();
        font2.setPointSize(8);
        lineEdit.setFont(font2);

        gridLayout.addWidget(lineEdit, 0, 2, 1, 1);

        label_2 = new QLabel(loginForm);
        label_2.setObjectName("label_2");
        QFont font3 = new QFont();
        font3.setPointSize(8);
        label_2.setFont(font3);
        label_2.setFrameShape(com.trolltech.qt.gui.QFrame.Shape.NoFrame);
        label_2.setAlignment(com.trolltech.qt.core.Qt.AlignmentFlag.createQFlags(com.trolltech.qt.core.Qt.AlignmentFlag.AlignRight,com.trolltech.qt.core.Qt.AlignmentFlag.AlignVCenter));

        gridLayout.addWidget(label_2, 1, 0, 1, 1);

        lineEdit_2 = new QLineEdit(loginForm);
        lineEdit_2.setObjectName("lineEdit_2");
        QFont font4 = new QFont();
        font4.setPointSize(8);
        lineEdit_2.setFont(font4);
        lineEdit_2.setEchoMode(com.trolltech.qt.gui.QLineEdit.EchoMode.Password);

        gridLayout.addWidget(lineEdit_2, 1, 2, 1, 1);


        gridLayout_2.addLayout(gridLayout, 3, 0, 1, 2);

        QWidget.setTabOrder(lineEdit, lineEdit_2);
        retranslateUi(loginForm);

        loginForm.connectSlotsByName();
    } // setupUi

    void retranslateUi(QWidget loginForm)
    {
        loginForm.setWindowTitle(com.trolltech.qt.core.QCoreApplication.translate("loginForm", "Login Form", null));
        label_3.setText(com.trolltech.qt.core.QCoreApplication.translate("loginForm", "Enter login and password to start:", null));
        label.setText(com.trolltech.qt.core.QCoreApplication.translate("loginForm", "Login:  ", null));
        label_2.setText(com.trolltech.qt.core.QCoreApplication.translate("loginForm", "Password: ", null));
    } // retranslateUi

}

