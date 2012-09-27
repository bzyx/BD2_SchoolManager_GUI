/********************************************************************************
** Form generated from reading ui file 'EditContestType.jui'
**
** Created: Cz 27. wrz 00:54:21 2012
**      by: Qt User Interface Compiler version 4.5.2
**
** WARNING! All changes made in this file will be lost when recompiling ui file!
********************************************************************************/

package pl.polsl.bd2.gui.forms;

import com.trolltech.qt.core.*;
import com.trolltech.qt.gui.*;

public class Ui_EditContestType implements com.trolltech.qt.QUiForm<QDialog>
{
    public QGridLayout gridLayout_2;
    public QVBoxLayout verticalLayout;
    public QHBoxLayout horizontalLayout;
    public QGridLayout gridLayout;
    public QLabel label_2;
    public QSpacerItem verticalSpacer;
    public QLabel labelDescription;
    public QLineEdit lineEditCompetiononType;
    public QListView listKonkursTyp;
    public QDialogButtonBox buttonBox;

    public Ui_EditContestType() { super(); }

    public void setupUi(QDialog EditContestType)
    {
        EditContestType.setObjectName("EditContestType");
        EditContestType.resize(new QSize(400, 300).expandedTo(EditContestType.minimumSizeHint()));
        gridLayout_2 = new QGridLayout(EditContestType);
        gridLayout_2.setObjectName("gridLayout_2");
        verticalLayout = new QVBoxLayout();
        verticalLayout.setObjectName("verticalLayout");
        horizontalLayout = new QHBoxLayout();
        horizontalLayout.setObjectName("horizontalLayout");

        verticalLayout.addLayout(horizontalLayout);


        gridLayout_2.addLayout(verticalLayout, 0, 0, 1, 1);

        gridLayout = new QGridLayout();
        gridLayout.setObjectName("gridLayout");
        label_2 = new QLabel(EditContestType);
        label_2.setObjectName("label_2");

        gridLayout.addWidget(label_2, 1, 0, 1, 1);

        verticalSpacer = new QSpacerItem(20, 40, com.trolltech.qt.gui.QSizePolicy.Policy.Minimum, com.trolltech.qt.gui.QSizePolicy.Policy.Expanding);

        gridLayout.addItem(verticalSpacer, 2, 0, 1, 1);

        labelDescription = new QLabel(EditContestType);
        labelDescription.setObjectName("labelDescription");

        gridLayout.addWidget(labelDescription, 0, 0, 1, 1);

        lineEditCompetiononType = new QLineEdit(EditContestType);
        lineEditCompetiononType.setObjectName("lineEditCompetiononType");

        gridLayout.addWidget(lineEditCompetiononType, 0, 1, 1, 1);

        listKonkursTyp = new QListView(EditContestType);
        listKonkursTyp.setObjectName("listKonkursTyp");

        gridLayout.addWidget(listKonkursTyp, 2, 1, 1, 1);


        gridLayout_2.addLayout(gridLayout, 1, 0, 1, 1);

        buttonBox = new QDialogButtonBox(EditContestType);
        buttonBox.setObjectName("buttonBox");
        buttonBox.setFocusPolicy(com.trolltech.qt.core.Qt.FocusPolicy.TabFocus);
        buttonBox.setStandardButtons(com.trolltech.qt.gui.QDialogButtonBox.StandardButton.createQFlags(com.trolltech.qt.gui.QDialogButtonBox.StandardButton.Cancel,com.trolltech.qt.gui.QDialogButtonBox.StandardButton.Save));

        gridLayout_2.addWidget(buttonBox, 2, 0, 1, 1);

        retranslateUi(EditContestType);

        EditContestType.connectSlotsByName();
    } // setupUi

    void retranslateUi(QDialog EditContestType)
    {
        EditContestType.setWindowTitle(com.trolltech.qt.core.QCoreApplication.translate("EditContestType", "Dialog", null));
        label_2.setText(com.trolltech.qt.core.QCoreApplication.translate("EditContestType", "Typ konkursu: ", null));
        labelDescription.setText(com.trolltech.qt.core.QCoreApplication.translate("EditContestType", "Nazwa konkursu: ", null));
    } // retranslateUi

}

