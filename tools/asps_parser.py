from wxPython.wx import *
import re

normal_skill = re.compile('.*new SkillInfo(.*)\.setAsBasic')
def parseline(line):
	if(normal_skill.match(line)):
		print line,
	
if __name__ == "__main__":
	application = wxPySimpleApp()
	dialog = wxFileDialog ( None, style = wxOPEN )
	if dialog.ShowModal() == wxID_OK:
		print 'Selected:', dialog.GetPath()
		
		with open(dialog.GetPath(),"r") as f:
			for line in f:
				parseline(line)


