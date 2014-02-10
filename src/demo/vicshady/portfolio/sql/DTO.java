/* HISTORY
 * CATEGORY 		:- ACTIVITY
 * DEVELOPER		:- VIKALP PATEL
 * AIM			    :- ADD IPD ACTIVITY
 * DESCRIPTION 		:- SAVE IPD
 * 
 * S - START E- END  C- COMMENTED  U -EDITED A -ADDED
 * --------------------------------------------------------------------------------------------------------------------
 * INDEX       DEVELOPER		DATE			FUNCTION		DESCRIPTION
 * --------------------------------------------------------------------------------------------------------------------
 * --------------------------------------------------------------------------------------------------------------------
 */
package demo.vicshady.portfolio.sql;

public class DTO {

	String text;
	int resourceId;
	int actionIcon;
	public DTO(String text, int resourceId, int actionIcon) {
		this.text = text;
		this.resourceId = resourceId;
		this.actionIcon = actionIcon;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getResourceId() {
		return resourceId;
	}
	public void setResourceId(int resourceId) {
		this.resourceId = resourceId;
	}
	public int getActionIcon() {
		return actionIcon;
	}
	public void setActionIcon(int actionIcon) {
		this.actionIcon = actionIcon;
	}
}
