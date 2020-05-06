package org.application;

import java.util.List;

public interface IDatabaseHandler {
    public List<Program> getPrograms();
    public int getProgramID(String productionTitle);
    public boolean createProgram(Program program);
    public boolean updateProgram();
    public boolean deleteProgram();

    public List<Credits> getCredits();
    public Credits getCredit(int id);
    public boolean createCredit(Credits credit);
    public boolean updateCredit();
    public boolean deleteCredit();
}
