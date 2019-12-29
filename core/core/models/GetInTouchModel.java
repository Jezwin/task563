package com.auki.core.models;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class GetInTouchModel {
	
	@Inject
	private String title;
	
	@Inject
	private String subtitle;
	
	@Inject
	private Resource icons;
	
	public String getTitle() {
		return title;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public Resource getIcons() {
		return icons;
	}
	
}
