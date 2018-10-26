package com.groupproject.group.Account.LoginAccount.UserAccount;

public class UserAccountNode {
    private UserAccount data;
    private UserAccountNode link;

    public UserAccountNode(UserAccount data, UserAccountNode link){
        this.data = data;
        this.link = link;
    }

    // Getters and setters
    public void setData(UserAccount data){this.data = data;}
    public UserAccount getData(){return data;}
    public void setLink(UserAccountNode link){this.link = link;}
    public UserAccountNode getLink(){return link;}

    /** Checks if two objects are the same, according to their data.
     * Post-condition: Returns false if the objects aren't UserAccountNodes. Returns true or false if they are the same type.  */
    public boolean equals(Object otherObj){
        if(!(otherObj instanceof UserAccountNode)) {
            return false;
        }

        UserAccountNode otherNode = (UserAccountNode)otherObj;

        // Return a compound equality statement. Ensuring both pieces of data are have the same contents.
        return (data.equals(otherNode.getData()) && link.getData().equals(otherNode.getLink().getData()));
    }
}
