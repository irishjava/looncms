package com.callaway.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ScoreBoard implements Serializable{
    public List<ScoreBoardRow> rows = new ArrayList<ScoreBoardRow>();
}