# USE CASE: 1 Produce a Report on the most populated cities in the world

## CHARACTERISTIC INFORMATION

### Goal in Context

As a Geographer I would like to produce a report on the most populated cities in the world.

### Scope

Company.

### Level

Primary task.

### Preconditions

Database contains current city population data.

### Success End Condition

A report is available for the Geographer with the most populated cities in the world.

### Failed End Condition

No report is produced.

### Primary Actor

Geographer

### Trigger

A request for population information is made by the geographer.

## MAIN SUCCESS SCENARIO

1. Geographer requests population information for the most populated cities.
2. Cities are sorted by population.
3. Most populated cities are extracted.
4. Report is provided to geographer.

## EXTENSIONS

3. **Country does not exist**:
    1. Inform the geographer the country does not exist.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0