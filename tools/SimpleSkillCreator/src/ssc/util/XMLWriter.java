package ssc.util;

import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import skillsplanner.skills.SkillsTemplate;
import ssc.mapper.SkillMapper;

public class XMLWriter {

	public static String makeXML(SkillsTemplate st) {
		XMLOutputter outputter = new XMLOutputter();
		Format format = outputter.getFormat();
		format.setIndent("    ");
		format.setLineSeparator("\n");
		outputter.setFormat(format);
		
		return outputter.outputString(SkillMapper.makeDocument(st));
	}

}
