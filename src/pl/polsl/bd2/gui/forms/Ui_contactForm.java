/********************************************************************************
** Form generated from reading ui file 'ContactForm.jui'
**
** Created: Œr 19. wrz 23:23:09 2012
**      by: Qt User Interface Compiler version 4.5.2
**
** WARNING! All changes made in this file will be lost when recompiling ui file!
********************************************************************************/

package pl.polsl.bd2.gui.forms;

import com.trolltech.qt.core.*;
import com.trolltech.qt.gui.*;

public class Ui_contactForm implements com.trolltech.qt.QUiForm<QDialog>
{
    public QGridLayout gridLayout;
    public QLabel label;
    public QLineEdit topic;
    public QLabel label_2;
    public QLineEdit fromPerson;
    public QLabel label_3;
    public QLineEdit toPerson;
    public QPushButton findButton;
    public QPlainTextEdit messageText;
    public QDialogButtonBox buttonBox;

    public Ui_contactForm() { super(); }

    public void setupUi(QDialog contactForm)
    {
        contactForm.setObjectName("contactForm");
        contactForm.resize(new QSize(400, 300).expandedTo(contactForm.minimumSizeHint()));
        gridLayout = new QGridLayout(contactForm);
        gridLayout.setObjectName("gridLayout");
        label = new QLabel(contactForm);
        label.setObjectName("label");

        gridLayout.addWidget(label, 0, 0, 1, 1);

        topic = new QLineEdit(contactForm);
        topic.setObjectName("topic");

        gridLayout.addWidget(topic, 0, 1, 1, 1);

        label_2 = new QLabel(contactForm);
        label_2.setObjectName("label_2");

        gridLayout.addWidget(label_2, 1, 0, 1, 1);

        fromPerson = new QLineEdit(contactForm);
        fromPerson.setObjectName("fromPerson");
        fromPerson.setEnabled(false);
        fromPerson.setReadOnly(true);

        gridLayout.addWidget(fromPerson, 1, 1, 1, 1);

        label_3 = new QLabel(contactForm);
        label_3.setObjectName("label_3");

        gridLayout.addWidget(label_3, 2, 0, 1, 1);

        toPerson = new QLineEdit(contactForm);
        toPerson.setObjectName("toPerson");

        gridLayout.addWidget(toPerson, 2, 1, 1, 1);

        findButton = new QPushButton(contactForm);
        findButton.setObjectName("findButton");

        gridLayout.addWidget(findButton, 2, 2, 1, 1);

        messageText = new QPlainTextEdit(contactForm);
        messageText.setObjectName("messageText");

        gridLayout.addWidget(messageText, 3, 0, 1, 3);

        buttonBox = new QDialogButtonBox(contactForm);
        buttonBox.setObjectName("buttonBox");
        buttonBox.setFocusPolicy(com.trolltech.qt.core.Qt.FocusPolicy.TabFocus);
        buttonBox.setStandardButtons(com.trolltech.qt.gui.QDialogButtonBox.StandardButton.createQFlags(com.trolltech.qt.gui.QDialogButtonBox.StandardButton.Cancel,com.trolltech.qt.gui.QDialogButtonBox.StandardButton.Yes));
        buttonBox.setCenterButtons(false);

        gridLayout.addWidget(buttonBox, 4, 0, 1, 3);

        retranslateUi(contactForm);

        contactForm.connectSlotsByName();
    } // setupUi

    void retranslateUi(QDialog contactForm)
    {
        contactForm.setWindowTitle(com.trolltech.qt.core.QCoreApplication.translate("contactForm", "Contact Form - Send Message?", null));
        label.setText(com.trolltech.qt.core.QCoreApplication.translate("contactForm", "Topic:", null));
        label_2.setText(com.trolltech.qt.core.QCoreApplication.translate("contactForm", "From:", null));
        label_3.setText(com.trolltech.qt.core.QCoreApplication.translate("contactForm", "To:", null));
        findButton.setText(com.trolltech.qt.core.QCoreApplication.translate("contactForm", "Find", null));
    } // retranslateUi

}

