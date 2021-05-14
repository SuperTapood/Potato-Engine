package potato.fonts;

import potato.GlobalData;

import java.lang.reflect.Field;

public class Query {
    char[] template;
    char placeholder;
    String[] attNames;

    public Query(String template, char ph, String[] attributeNames) {
        this.template = template.toCharArray();
        placeholder = ph;
        attNames = attributeNames;
    }

    public Query(String template, String[] attributeNames) {
        this.template = template.toCharArray();
        placeholder = '#';
        attNames = attributeNames;
    }

    public String query() {
        StringBuilder sb = new StringBuilder();
        int index = 0;
        for (char c : template) {
            if (c == placeholder) {
                sb.append(getAttribute(attNames[index]));
                index++;
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public Object getAttribute(String name) {
        Class<?> c = GlobalData.class;
        try {
            Field f = c.getDeclaredField(name);
            f.setAccessible(true);
            return f.get(c);
        } catch (Exception ignored) {
            // for now the exception will be ignored
        }
        return null;
    }

    public String toString() {
        return query();
    }
}
