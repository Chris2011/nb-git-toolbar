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
        id = "pro.elab.git.releaseFinishAction"
)
@ActionRegistration(
        iconBase = "pro/elab/git/rf.png",
        displayName = "#CTL_releaseFinishAction"
)
@ActionReference(path = "Toolbars/Git", position = 4)
@Messages("CTL_releaseFinishAction=Finish release")
public final class releaseFinishAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        currentProjectInfo cpi = new currentProjectInfo();
        if (cpi.getName() != null) {
            gitFlow hf = new gitFlow(cpi.getDir());
            hf.releaseFinish();
        }
    }

}
