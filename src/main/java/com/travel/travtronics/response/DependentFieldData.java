package com.travel.travtronics.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DependentFieldData {

	private Integer dependentField;
	private Boolean isReadOnly;
    private Boolean isHide;
    private String defaultValue;
    private String paramKey;
    private Boolean isValueMatch;
    private String matchValue;
    private ConfigResponse inputConfigResponse;
}
