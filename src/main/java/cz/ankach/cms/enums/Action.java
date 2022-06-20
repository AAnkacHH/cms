package cz.ankach.cms.enums;

public final class Action {
    public static final String VIEW_COMMON_ARTICLES = "VIEW_COMMON_ARTICLES";
    public static final String VIEW_SPECIFIC_ARTICLES = "VIEW_SPECIFIC_ARTICLES";
    public static final String VIEW_CONFIGURATIONS = "VIEW_CONFIGURATIONS";
    public static final String VIEW_ADMIN_PANEL = "VIEW_ADMIN_PANEL";
    public static final String VIEW_COMMENTS = "VIEW_COMMENTS";

    public static final String EDIT_COMMENTS = "EDIT_COMMENTS";
    public static final String EDIT_COMMON_ARTICLES = "EDIT_COMMON_ARTICLES";
    public static final String EDIT_SPECIFIC_ARTICLES = "EDIT_SPECIFIC_ARTICLES";
    public static final String EDIT_CONFIGURATIONS = "EDIT_CONFIGURATIONS";
    public static final String EDIT_ADMIN_PANEL = "EDIT_ADMIN_PANEL";

    public static final String WRITE_ARTICLE = "WRITE_ARTICLE";
    public static final String WRITE_COMMENTS = "WRITE_ARTICLE";

    public static String[] getActions() {
        return new String[] {
                VIEW_COMMON_ARTICLES, VIEW_CONFIGURATIONS, VIEW_SPECIFIC_ARTICLES, VIEW_ADMIN_PANEL,
                EDIT_COMMON_ARTICLES, EDIT_CONFIGURATIONS, EDIT_SPECIFIC_ARTICLES, EDIT_ADMIN_PANEL,
                WRITE_ARTICLE, WRITE_COMMENTS, EDIT_COMMENTS, VIEW_COMMENTS
        };
    }
}
