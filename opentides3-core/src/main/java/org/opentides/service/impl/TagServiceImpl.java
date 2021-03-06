package org.opentides.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.opentides.bean.Tag;
import org.opentides.dao.TagDao;
import org.opentides.service.TagService;
import org.opentides.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author AJ
 *
 */
@Service(value="tagService")
public class TagServiceImpl extends BaseCrudServiceImpl<Tag>
                implements TagService {
	@Autowired
	public void setTagDao(TagDao tagDao) {
		this.dao = tagDao;
	}
	
	@Override
	public List<Tag> createTags(String csTags[]) {
		List<String> items = Arrays.asList( csTags );
		List<Tag> tags = new ArrayList<Tag>();
		
		for (String item : items) {
			
			TagDao dao = (TagDao) getDao();
			Tag existingTag = dao.loadByText(item);
			
			if(!(StringUtil.isEmpty(item) || "".equals(item))) {
				if(existingTag != null) {
					tags.add(existingTag);
				} else {
					Tag tag = new Tag();
					tag.setTagText(item);
					tags.add(tag);
				}
			}
			
		}
		
		return tags;
	}
        
}
