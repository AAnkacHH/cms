package cz.ankach.cms.api.requests;

import java.util.List;

public class UpdateArticleRequest {
    public String title;
    public String content;

    public List<Long> tagIds; // todo
}
