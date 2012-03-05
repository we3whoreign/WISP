package skillsplanner.utils;

import java.util.Comparator;

import skillsplanner.beans.DFOClass;

public class DFOClassComparator implements Comparator<DFOClass>{

	@Override
	public int compare(DFOClass arg0, DFOClass arg1) {
		return arg0.getUniqueName().compareTo(arg1.getUniqueName());
	}

}
