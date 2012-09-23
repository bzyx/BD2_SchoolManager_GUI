/********************************************************************************
** Form generated from reading ui file 'JustificationForm.jui'
**
** Created: Œr 19. wrz 23:23:09 2012
**      by: Qt User Interface Compiler version 4.5.2
**
** WARNING! All changes made in this file will be lost when recompiling ui file!
********************************************************************************/

package pl.polsl.bd2.gui.forms;

import com.trolltech.qt.core.*;
import com.trolltech.qt.gui.*;

public class Ui_justificationForm implements com.trolltech.qt.QUiForm<QWidget>
{
    public QGridLayout gridLayout;
    public QLabel label;
    public QLabel label_2;
    public QSpinBox spinBoxHowMuchLection;
    public QLabel label_3;
    public QPlainTextEdit plainTextEditJustification;
    public QDialogButtonBox buttonBoxJustification;
    public QSpacerItem horizontalSpacer;
    public QLabel labelDateJustification;

    public Ui_justificationForm() { super(); }

    public void setupUi(QWidget justificationForm)
    {
        justificationForm.setObjectName("justificationForm");
        justificationForm.resize(new QSize(400, 300).expandedTo(justificationForm.minimumSizeHint()));
        gridLayout = new QGridLayout(justificationForm);
        gridLayout.setObjectName("gridLayout");
        label = new QLabel(justificationForm);
        label.setObjectName("label");
        QSizePolicy sizePolicy = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Maximum, com.trolltech.qt.gui.QSizePolicy.Policy.Preferred);
        sizePolicy.setHorizontalStretch((byte)0);
        sizePolicy.setVerticalStretch((byte)0);
        sizePolicy.setHeightForWidth(label.sizePolicy().hasHeightForWidth());
        label.setSizePolicy(sizePolicy);
        QFont font = new QFont();
        font.setPointSize(12);
        label.setFont(font);

        gridLayout.addWidget(label, 0, 0, 1, 1);

        label_2 = new QLabel(justificationForm);
        label_2.setObjectName("label_2");
        QSizePolicy sizePolicy1 = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Maximum, com.trolltech.qt.gui.QSizePolicy.Policy.Preferred);
        sizePolicy1.setHorizontalStretch((byte)0);
        sizePolicy1.setVerticalStretch((byte)0);
        sizePolicy1.setHeightForWidth(label_2.sizePolicy().hasHeightForWidth());
        label_2.setSizePolicy(sizePolicy1);
        QFont font1 = new QFont();
        font1.setPointSize(12);
        label_2.setFont(font1);

        gridLayout.addWidget(label_2, 1, 0, 1, 1);

        spinBoxHowMuchLection = new QSpinBox(justificationForm);
        spinBoxHowMuchLection.setObjectName("spinBoxHowMuchLection");

        gridLayout.addWidget(spinBoxHowMuchLection, 1, 2, 1, 1);

        label_3 = new QLabel(justificationForm);
        label_3.setObjectName("label_3");
        QSizePolicy sizePolicy2 = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Maximum, com.trolltech.qt.gui.QSizePolicy.Policy.Preferred);
        sizePolicy2.setHorizontalStretch((byte)0);
        sizePolicy2.setVerticalStretch((byte)0);
        sizePolicy2.setHeightForWidth(label_3.sizePolicy().hasHeightForWidth());
        label_3.setSizePolicy(sizePolicy2);
        QFont font2 = new QFont();
        font2.setPointSize(12);
        label_3.setFont(font2);

        gridLayout.addWidget(label_3, 2, 0, 1, 3);

        plainTextEditJustification = new QPlainTextEdit(justificationForm);
        plainTextEditJustification.setObjectName("plainTextEditJustification");

        gridLayout.addWidget(plainTextEditJustification, 3, 0, 1, 3);

        buttonBoxJustification = new QDialogButtonBox(justificationForm);
        buttonBoxJustification.setObjectName("buttonBoxJustification");
        buttonBoxJustification.setStandardButtons(com.trolltech.qt.gui.QDialogButtonBox.StandardButton.createQFlags(com.trolltech.qt.gui.QDialogButtonBox.StandardButton.Cancel,com.trolltech.qt.gui.QDialogButtonBox.StandardButton.Ok));

        gridLayout.addWidget(buttonBoxJustification, 4, 2, 1, 1);

        horizontalSpacer = new QSpacerItem(40, 20, com.trolltech.qt.gui.QSizePolicy.Policy.Expanding, com.trolltech.qt.gui.QSizePolicy.Policy.Minimum);

        gridLayout.addItem(horizontalSpacer, 0, 1, 1, 1);

        labelDateJustification = new QLabel(justificationForm);
        labelDateJustification.setObjectName("labelDateJustification");

        gridLayout.addWidget(labelDateJustification, 0, 2, 1, 1);

        retranslateUi(justificationForm);

        justificationForm.connectSlotsByName();
    } // setupUi

    void retranslateUi(QWidget justificationForm)
    {
        justificationForm.setWindowTitle(com.trolltech.qt.core.QCoreApplication.translate("justificationForm", "Form", null));
        label.setText(com.trolltech.qt.core.QCoreApplication.translate("justificationForm", "Date: ", null));
        label_2.setText(com.trolltech.qt.core.QCoreApplication.translate("justificationForm", "How much lection: ", null));
        label_3.setText(com.trolltech.qt.core.QCoreApplication.translate("justificationForm", "Motive: ", null));
        labelDateJustification.setText(com.trolltech.qt.core.QCoreApplication.translate("justificationForm", "TextLabel", null));
    } // retranslateUi

}

