/********************************************************************************
** Form generated from reading ui file 'contactForm.jui'
**
** Created: Œr 11. kwi 15:18:55 2012
**      by: Qt User Interface Compiler version 4.5.2
**
** WARNING! All changes made in this file will be lost when recompiling ui file!
********************************************************************************/

package pl.polsl.bd2.ui;

import com.trolltech.qt.core.*;
import com.trolltech.qt.gui.*;

public class Ui_contactForm implements com.trolltech.qt.QUiForm<QDialog>
{
    public QVBoxLayout verticalLayout_2;
    public QVBoxLayout verticalLayout;
    public QGridLayout gridLayout;
    public QLineEdit toPerson;
    public QPushButton findButton;
    public QLineEdit fromPerson;
    public QPlainTextEdit messageText;
    public QDialogButtonBox buttonBox;

    public Ui_contactForm() { super(); }

    public void setupUi(QDialog contactForm)
    {
        contactForm.setObjectName("contactForm");
        contactForm.resize(new QSize(400, 300).expandedTo(contactForm.minimumSizeHint()));
        verticalLayout_2 = new QVBoxLayout(contactForm);
        verticalLayout_2.setObjectName("verticalLayout_2");
        verticalLayout = new QVBoxLayout();
        verticalLayout.setObjectName("verticalLayout");
        gridLayout = new QGridLayout();
        gridLayout.setObjectName("gridLayout");
        toPerson = new QLineEdit(contactForm);
        toPerson.setObjectName("toPerson");

        gridLayout.addWidget(toPerson, 1, 0, 1, 1);

        findButton = new QPushButton(contactForm);
        findButton.setObjectName("findButton");

        gridLayout.addWidget(findButton, 1, 1, 1, 1);

        fromPerson = new QLineEdit(contactForm);
        fromPerson.setObjectName("fromPerson");
        fromPerson.setEnabled(false);
        fromPerson.setReadOnly(true);

        gridLayout.addWidget(fromPerson, 0, 0, 1, 1);


        verticalLayout.addLayout(gridLayout);

        messageText = new QPlainTextEdit(contactForm);
        messageText.setObjectName("messageText");

        verticalLayout.addWidget(messageText);


        verticalLayout_2.addLayout(verticalLayout);

        buttonBox = new QDialogButtonBox(contactForm);
        buttonBox.setObjectName("buttonBox");
        buttonBox.setFocusPolicy(com.trolltech.qt.core.Qt.FocusPolicy.TabFocus);
        buttonBox.setStandardButtons(com.trolltech.qt.gui.QDialogButtonBox.StandardButton.createQFlags(com.trolltech.qt.gui.QDialogButtonBox.StandardButton.Cancel,com.trolltech.qt.gui.QDialogButtonBox.StandardButton.Yes));

        verticalLayout_2.addWidget(buttonBox);

        retranslateUi(contactForm);

        contactForm.connectSlotsByName();
    } // setupUi

    void retranslateUi(QDialog contactForm)
    {
        contactForm.setWindowTitle(com.trolltech.qt.core.QCoreApplication.translate("contactForm", "Contact Form - Send Message?", null));
        findButton.setText(com.trolltech.qt.core.QCoreApplication.translate("contactForm", "Find", null));
    } // retranslateUi

}

