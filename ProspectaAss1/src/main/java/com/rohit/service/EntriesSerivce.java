package com.rohit.service;

import java.util.List;

import com.rohit.exceptions.InvalidEntryException;
import com.rohit.models.Entries;

public interface EntriesSerivce {
	
	public String saveEntry(Entries entries)throws InvalidEntryException; 
	public List<Entries> getAllEntries(); 

}
