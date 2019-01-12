package com.dx.ss.hankoo.dal.model;

import com.dx.ss.data.beans.DocumentBean;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class ParticipantModel extends DocumentBean {

    private String name;

    private String info;

    private String surprise;

    private String first;

    private String second;

    private String third;

    private String forth;

    @Override
    public ArrayList<String> propertiesAssembly() {
        return new ArrayList<String>() {{
            add("name");
            add("info");
            add("surprise");
            add("first");
            add("second");
            add("third");
            add("forth");
        }};
    }
}
