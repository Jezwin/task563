package com.auki.core.models;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class WhoIsYourHeroModel {
	
	@Inject
	private String heroTitle;
	
	@Inject
	private String heroDescription;
	
	@Inject
	private String heroSubtitle;
	
	@Inject
	private String posterPath;
	
	@Inject
	private String videoPath;

	public String getHeroTitle() {
		return heroTitle;
	}

	public String getHeroDescription() {
		return heroDescription;
	}

	public String getHeroSubtitle() {
		return heroSubtitle;
	}

	public String getPosterPath() {
		return posterPath;
	}

	public String getVideoPath() {
		return videoPath;
	}
	

}
