/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pro.elab.git;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "Git",
        id = "pro.elab.git.featureStartAction"
)
@ActionRegistration(
        iconBase = "pro/elab/git/fs.png",
        displayName = "#CTL_featureStartAction"
)
@ActionReference(path = "Toolbars/Git", position = 0)
@Messages("CTL_featureStartAction=Create feature")
public final class featureStartAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        currentProjectInfo cpi = new currentProjectInfo();
        if (cpi.getName() != null) {
            FormGetTag f = new FormGetTag(null, true, cpi.projectDir, 1);
            f.show();
        }
    }

}
