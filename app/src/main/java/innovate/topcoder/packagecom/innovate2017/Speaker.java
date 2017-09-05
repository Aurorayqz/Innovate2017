package innovate.topcoder.packagecom.innovate2017;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Aurorayqz on 2017/8/24.
 */

public class Speaker implements Serializable {
    /**
     * <p>
     * The speaker name.
     * </p>
     */
    private String name;
    /**
     * <p>
     * The speaker title.
     * </p>
     */
    private String title;
/**
 * <p>
 * The speaker picture.
 * </p>
 */private String picture;
    /**
     * <p>
     * The speaker details.
     * </p>
     */
    private String details;
    /**
     * <p>
     * The speaker session identificators.
     * </p>
     */
    private List<String> sessionIds;
/**
 * <p>
 * Simple getter for a namesake field.
 * </p>
 *
 * @return value of a namesake field.
 */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public List<String> getSessionIds() {
        return sessionIds;
    }

    public void setSessionIds(List<String> sessionIds) {
        this.sessionIds = sessionIds;
    }
}
