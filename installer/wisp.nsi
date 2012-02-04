# set up the name of the installer
outfile "WISP.exe"

#select install directory. Default to Desktop
installDir $DESKTOP

#create default section
section ""
	
	# define the output path
	setOutPath $INSTDIR\WISP

	# define what to install 
	file "C:\shared\workspace\github\WISP\jars\DFOPlanner.0.01a-simple.jar"
	file /r "C:\shared\workspace\github\WISP\libs"
	

	#create shortcut
	createShortCut "$SMPROGRAMS\WISP.lnk" "$INSTDIR\WISP.exe"

sectionEnd

