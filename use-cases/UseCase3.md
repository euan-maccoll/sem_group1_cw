# USE CASE: 3 Produce a Report containing information about a country and its cities.

## CHARACTERISTIC INFORMATION

### Goal in Context

As an Economist I would like to be able to access country and city reports for my research

### Scope

Company.

### Level

Primary task.

### Preconditions

We know the country and cities. Database contains the country and cities.

### Success End Condition

A report is available for the economist containing information about the country and cities.

### Failed End Condition

No report is produced.

### Primary Actor

Economist

### Trigger

A request for country and city information is made by the economist.

## MAIN SUCCESS SCENARIO

1. Economist requests information for country and cities.
2. Search for country and cities.
3. Extracts information for the country and cities selected.
4. Report is provided to economist.

## EXTENSIONS

3. **Country And/Or City does not exist**:
    1. Inform the economist the country and/or city does not exist.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0