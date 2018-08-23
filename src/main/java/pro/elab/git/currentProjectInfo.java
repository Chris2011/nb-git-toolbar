/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pro.elab.git;

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import org.netbeans.api.project.FileOwnerQuery;
import org.netbeans.api.project.Project;
import org.netbeans.api.project.ProjectInformation;
import org.netbeans.api.project.ProjectUtils;
import org.openide.filesystems.FileObject;
import org.openide.loaders.DataObject;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;
import org.openide.util.NbPreferences;
import org.openide.windows.Mode;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;

/**
 *
 * @author eugine
 */
public class currentProjectInfo {

    String projectName = null;
    String projectDir = null;
    String fileName = null;
    Project project = null;

    currentProjectInfo() {
        try {
            WindowManager manager = WindowManager.getDefault();
            for (Mode m : manager.getModes()) {
                if (m.getName().equals("editor")) {
                    for (TopComponent t : WindowManager.getDefault().getOpenedTopComponents(m)) {
                        String dName = t.getDisplayName();
                        if (dName != null) {
                            if (t.isShowing()) {
                                Node[] n = t.getActivatedNodes();
                                project = findProjectThatOwnsNode(n[0]);
                                projectName = getProjectName(project);
                                projectDir = getProjectDirectory(project);
                            }
                        }
                    }
                    break;
                }
            }
        } catch (Exception e) {
            //
        }
    }

    public String getName() {
        return projectName;
    }

    public String getDir() {
        return projectDir;
    }

    public void print() {
        System.out.println("PN:" + projectName);
        System.out.println("PD:" + projectDir);
    }

    private String getProjectDirectory(final ProjectInformation projectInformation) {
        return getProjectDirectory(projectInformation.getProject());
    }

    private String getProjectDirectory(final Project project) {
        FileObject projectDirectory = project.getProjectDirectory();
        return projectDirectory.getPath();
    }

    private String getProjectName(final Project project) {
        return getProjectName(ProjectUtils.getInformation(project));
    }

    private String getProjectName(final ProjectInformation projectInformation) {
        return projectInformation.getDisplayName();
    }

    /**
     * Preferences key for the active group ID.
     */
    private static final String KEY_ACTIVE = "active"; // NOI18N
    /**
     * Preferences key for display name of group.
     */
    protected static final String KEY_NAME = "name"; // NOI18N

    /**
     * Get the preference for the given node path.
     *
     * @param path configuration path like "org/netbeans/modules/projectui"
     * @return {@link Preferences} or null
     */
    private Preferences getPreferences(String path) {
        try {
            if (NbPreferences.root().nodeExists(path)) {
                return NbPreferences.root().node(path);
            }
        } catch (BackingStoreException ex) {
            Exceptions.printStackTrace(ex);
        }
        return null;
    }

    /**
     *
     * @return name of the current project group or null
     */
    private String getActiveProjectGroup() {
        Preferences groupNode = getPreferences("org/netbeans/modules/projectui/groups");
        if (null != groupNode) {
            final String groupId = groupNode.get(KEY_ACTIVE, null);
            if (null != groupId) {
                final Preferences groupPref = getPreferences("org/netbeans/modules/projectui/groups" + "/" + groupId);
                if (null != groupPref) {
                    final String activeProjectGroup = groupPref.get(KEY_NAME, null);
                    return activeProjectGroup;
                }
            }
        }
        return null;
    }

    /**
     * Recursively searches the node hierarchy for the project that owns a node.
     *
     * @param node a node to test for a Project in its or its ancestor's lookup.
     * @return the Project that owns the node, or null if not found
     */
    private Project findProjectThatOwnsNode(Node node) {
        if (node != null) {
            Project project = node.getLookup().lookup(Project.class);
            if (project == null) {
                DataObject dataObject = node.getLookup().lookup(DataObject.class);
                if (dataObject != null) {
                    project = FileOwnerQuery.getOwner(dataObject.getPrimaryFile());
                }
            }
            return (project == null) ? findProjectThatOwnsNode(node.getParentNode()) : project;
        } else {
            return null;
        }
    }

}
