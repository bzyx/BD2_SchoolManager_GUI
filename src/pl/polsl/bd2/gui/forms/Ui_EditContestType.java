/********************************************************************************
** Form generated from reading ui file 'EditContestType.jui'
**
** Created: Wt 25. wrz 23:34:11 2012
**      by: Qt User Interface Compiler version 4.5.2
**
** WARNING! All changes made in this file will be lost when recompiling ui file!
********************************************************************************/

package pl.polsl.bd2.gui.forms;

import com.trolltech.qt.core.*;
import com.trolltech.qt.gui.*;

public class Ui_EditContestType implements com.trolltech.qt.QUiForm<QDialog>
{
    public QGridLayout gridLayout;
    public QVBoxLayout verticalLayout;
    public QHBoxLayout horizontalLayout;
    public QLabel label;
    public QLineEdit lineEditCompetiononType;
    public QPushButton pushButton;
    public QListView listView;
    public QDialogButtonBox buttonBox;
    public QLabel label_2;
    public QSpacerItem verticalSpacer;

    public Ui_EditContestType() { super(); }

    public void setupUi(QDialog EditContestType)
    {
        EditContestType.setObjectName("EditContestType");
        EditContestType.resize(new QSize(400, 300).expandedTo(EditContestType.minimumSizeHint()));
        gridLayout = new QGridLayout(EditContestType);
        gridLayout.setObjectName("gridLayout");
        verticalLayout = new QVBoxLayout();
        verticalLayout.setObjectName("verticalLayout");
        horizontalLayout = new QHBoxLayout();
        horizontalLayout.setObjectName("horizontalLayout");
        label = new QLabel(EditContestType);
        label.setObjectName("label");

        horizontalLayout.addWidget(label);

        lineEditCompetiononType = new QLineEdit(EditContestType);
        lineEditCompetiononType.setObjectName("lineEditCompetiononType");

        horizontalLayout.addWidget(lineEditCompetiononType);

        pushButton = new QPushButton(EditContestType);
        pushButton.setObjectName("pushButton");

        horizontalLayout.addWidget(pushButton);


        verticalLayout.addLayout(horizontalLayout);


        gridLayout.addLayout(verticalLayout, 0, 0, 1, 2);

        listView = new QListView(EditContestType);
        listView.setObjectName("listView");

        gridLayout.addWidget(listView, 1, 1, 2, 1);

        buttonBox = new QDialogButtonBox(EditContestType);
        buttonBox.setObjectName("buttonBox");
        buttonBox.setFocusPolicy(com.trolltech.qt.core.Qt.FocusPolicy.TabFocus);
        buttonBox.setStandardButtons(com.trolltech.qt.gui.QDialogButtonBox.StandardButton.createQFlags(com.trolltech.qt.gui.QDialogButtonBox.StandardButton.Cancel,com.trolltech.qt.gui.QDialogButtonBox.StandardButton.Ok));

        gridLayout.addWidget(buttonBox, 4, 0, 1, 2);

        label_2 = new QLabel(EditContestType);
        label_2.setObjectName("label_2");

        gridLayout.addWidget(label_2, 1, 0, 1, 1);

        verticalSpacer = new QSpacerItem(20, 40, com.trolltech.qt.gui.QSizePolicy.Policy.Minimum, com.trolltech.qt.gui.QSizePolicy.Policy.Expanding);

        gridLayout.addItem(verticalSpacer, 2, 0, 1, 1);

        retranslateUi(EditContestType);

        EditContestType.connectSlotsByName();
    } // setupUi

    void retranslateUi(QDialog EditContestType)
    {
        EditContestType.setWindowTitle(com.trolltech.qt.core.QCoreApplication.translate("EditContestType", "Dialog", null));
        label.setText(com.trolltech.qt.core.QCoreApplication.translate("EditContestType", "Dodaj/Edytuj konkurs:", null));
        pushButton.setText(com.trolltech.qt.core.QCoreApplication.translate("EditContestType", "Zapisz", null));
        label_2.setText(com.trolltech.qt.core.QCoreApplication.translate("EditContestType", "Typy konkursu", null));
    } // retranslateUi

}

