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
        id = "pro.elab.git.featureFinishAction"
)
@ActionRegistration(
        iconBase = "pro/elab/git/ff.png",
        displayName = "#CTL_featureFinishAction"
)
@ActionReference(path = "Toolbars/Git", position = 1)
@Messages("CTL_featureFinishAction=Finish feature")
public final class featureFinishAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        currentProjectInfo cpi = new currentProjectInfo();
        if (cpi.getName() != null) {
            gitFlow hf = new gitFlow(cpi.getDir());
            hf.featureFinish();
        }
    }

}
