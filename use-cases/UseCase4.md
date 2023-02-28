# USE CASE: 4 Produce a Report on the population of cities

## CHARACTERISTIC INFORMATION

### Goal in Context

As a journalist, I want to be able to access city population reports, so that I can gather information for my articles about population trends and urbanization.

### Scope

Company.

### Level

Primary task.

### Preconditions

We know the city. Database contains the city.

### Success End Condition

A report is available for the journalist containing the population information of the city.

### Failed End Condition

No report is produced.

### Primary Actor

Journalist

### Trigger

A request for population information from a city is made by the journalist.

## MAIN SUCCESS SCENARIO

1. Journalist requests information for population from a city.
2. Search for city.
3. Extracts population information for the city selected.
4. Report is provided to journalist.

## EXTENSIONS

3. **City does not exist**:
    1. Inform the journalist the city does not exist.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0