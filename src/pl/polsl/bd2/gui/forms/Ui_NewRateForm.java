/********************************************************************************
** Form generated from reading ui file 'NewRateForm.jui'
**
** Created: Cz 27. wrz 01:29:28 2012
**      by: Qt User Interface Compiler version 4.5.2
**
** WARNING! All changes made in this file will be lost when recompiling ui file!
********************************************************************************/

package pl.polsl.bd2.gui.forms;

import com.trolltech.qt.core.*;
import com.trolltech.qt.gui.*;

public class Ui_NewRateForm implements com.trolltech.qt.QUiForm<QWidget>
{
    public QGridLayout gridLayout;
    public QLabel label;
    public QLabel label_2;
    public QSpinBox spinBox;
    public QDoubleSpinBox doubleSpinBox;

    public Ui_NewRateForm() { super(); }

    public void setupUi(QWidget NewRateForm)
    {
        NewRateForm.setObjectName("NewRateForm");
        NewRateForm.resize(new QSize(224, 69).expandedTo(NewRateForm.minimumSizeHint()));
        QSizePolicy sizePolicy = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Maximum, com.trolltech.qt.gui.QSizePolicy.Policy.Maximum);
        sizePolicy.setHorizontalStretch((byte)0);
        sizePolicy.setVerticalStretch((byte)0);
        sizePolicy.setHeightForWidth(NewRateForm.sizePolicy().hasHeightForWidth());
        NewRateForm.setSizePolicy(sizePolicy);
        gridLayout = new QGridLayout(NewRateForm);
        gridLayout.setObjectName("gridLayout");
        label = new QLabel(NewRateForm);
        label.setObjectName("label");
        QSizePolicy sizePolicy1 = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Minimum, com.trolltech.qt.gui.QSizePolicy.Policy.Minimum);
        sizePolicy1.setHorizontalStretch((byte)0);
        sizePolicy1.setVerticalStretch((byte)0);
        sizePolicy1.setHeightForWidth(label.sizePolicy().hasHeightForWidth());
        label.setSizePolicy(sizePolicy1);
        QFont font = new QFont();
        font.setPointSize(12);
        label.setFont(font);

        gridLayout.addWidget(label, 0, 0, 1, 1);

        label_2 = new QLabel(NewRateForm);
        label_2.setObjectName("label_2");
        QSizePolicy sizePolicy2 = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Minimum, com.trolltech.qt.gui.QSizePolicy.Policy.Minimum);
        sizePolicy2.setHorizontalStretch((byte)0);
        sizePolicy2.setVerticalStretch((byte)0);
        sizePolicy2.setHeightForWidth(label_2.sizePolicy().hasHeightForWidth());
        label_2.setSizePolicy(sizePolicy2);
        QFont font1 = new QFont();
        font1.setPointSize(12);
        label_2.setFont(font1);

        gridLayout.addWidget(label_2, 0, 1, 1, 1);

        spinBox = new QSpinBox(NewRateForm);
        spinBox.setObjectName("spinBox");
        QSizePolicy sizePolicy3 = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Maximum, com.trolltech.qt.gui.QSizePolicy.Policy.Minimum);
        sizePolicy3.setHorizontalStretch((byte)0);
        sizePolicy3.setVerticalStretch((byte)0);
        sizePolicy3.setHeightForWidth(spinBox.sizePolicy().hasHeightForWidth());
        spinBox.setSizePolicy(sizePolicy3);
        spinBox.setMinimumSize(new QSize(100, 25));
        spinBox.setMaximumSize(new QSize(100, 25));
        spinBox.setMinimum(1);
        spinBox.setMaximum(6);
        spinBox.setValue(5);

        gridLayout.addWidget(spinBox, 1, 0, 1, 1);

        doubleSpinBox = new QDoubleSpinBox(NewRateForm);
        doubleSpinBox.setObjectName("doubleSpinBox");
        QSizePolicy sizePolicy4 = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Minimum, com.trolltech.qt.gui.QSizePolicy.Policy.Minimum);
        sizePolicy4.setHorizontalStretch((byte)0);
        sizePolicy4.setVerticalStretch((byte)0);
        sizePolicy4.setHeightForWidth(doubleSpinBox.sizePolicy().hasHeightForWidth());
        doubleSpinBox.setSizePolicy(sizePolicy4);
        doubleSpinBox.setMinimumSize(new QSize(100, 25));
        doubleSpinBox.setMaximumSize(new QSize(100, 25));
        doubleSpinBox.setMinimum(0.5);
        doubleSpinBox.setMaximum(2);
        doubleSpinBox.setSingleStep(0.5);

        gridLayout.addWidget(doubleSpinBox, 1, 1, 1, 1);

        retranslateUi(NewRateForm);

        NewRateForm.connectSlotsByName();
    } // setupUi

    void retranslateUi(QWidget NewRateForm)
    {
        NewRateForm.setWindowTitle(com.trolltech.qt.core.QCoreApplication.translate("NewRateForm", "Form", null));
        label.setText(com.trolltech.qt.core.QCoreApplication.translate("NewRateForm", "Ocena", null));
        label_2.setText(com.trolltech.qt.core.QCoreApplication.translate("NewRateForm", "Waga", null));
    } // retranslateUi

}

