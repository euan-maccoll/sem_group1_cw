# USE CASE: 8 Produce a Report on the population of countries

## CHARACTERISTIC INFORMATION

### Goal in Context

As a government official, I want to be able to see detailed population data for different countries, so that I can make informed decisions about foreign aid, immigration policies, and other international issues.

### Scope

Company.

### Level

Primary task.

### Preconditions

We know the countries. Database contains the countries.

### Success End Condition

A report is available for the government official containing the population information of the countries.

### Failed End Condition

No report is produced.

### Primary Actor

Government Official

### Trigger

A request for population information from countries is made by the government official.

## MAIN SUCCESS SCENARIO

1. Government official requests information for population from countries.
2. Search for countries.
3. Extracts population information for the countries selected.
4. Report is provided to government official.

## EXTENSIONS

3. **Countries do not exist**:
    1. Inform the government official the countries do not exist.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0