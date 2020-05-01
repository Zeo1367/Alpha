package com.eduprimehub.alpha.models.objects;


import org.apache.commons.lang3.StringUtils;
import org.springframework.web.util.HtmlUtils;

import java.util.Objects;

public class Displayable extends TextualIdentifiable {
    private String text;

    public Displayable() {
    }

    public Displayable(String id, String text) {
        this.text = text != null ? HtmlUtils.htmlUnescape(text) : text;
        this.setId(id);
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text != null ? HtmlUtils.htmlUnescape(text) : text;
    }

    public String getId() {
        String id = super.getId();
        return StringUtils.isBlank(id) ? null : id;
    }

    public void setId(String id) {
        if (StringUtils.isBlank(id)) {
            super.setId((String)null);
        } else {
            super.setId(id);
        }

    }

    public String toString() {
        return String.format("id = %s, text = %s", this.getId(), this.getText());
    }

    public int hashCode() {
        return Objects.hashCode(new Object[]{this.getId(), this.text});
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof Displayable) {
            return this.hashCode() == obj.hashCode();
        } else {
            return false;
        }
    }
}
