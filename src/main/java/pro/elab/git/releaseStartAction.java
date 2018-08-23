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
        id = "pro.elab.git.releaseStartAction"
)
@ActionRegistration(
        iconBase = "pro/elab/git/rs.png",
        displayName = "#CTL_releaseStartAction"
)
@ActionReference(path = "Toolbars/Git", position = 3)
@Messages("CTL_releaseStartAction=Create release")
public final class releaseStartAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        currentProjectInfo cpi = new currentProjectInfo();
        if (cpi.getName() != null) {
            FormGetTag f = new FormGetTag(null, true, cpi.projectDir, 2);
            f.show();
        }
    }

}
