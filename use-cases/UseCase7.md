# USE CASE: 7 Produce a Report on the population and demographic of a country

## CHARACTERISTIC INFORMATION

### Goal in Context

As a business owner, I want to be able to see population and demographic data for different countries, so that I can make informed decisions about where to expand my business.

### Scope

Company.

### Level

Primary task.

### Preconditions

We know the country. Database contains the country.

### Success End Condition

A report is available for the business owner containing the population information of the country.

### Failed End Condition

No report is produced.

### Primary Actor

Business owner

### Trigger

A request for population and demographic information from a country is made by the business owner.

## MAIN SUCCESS SCENARIO

1. Business owner requests information for population and demographics from a country.
2. Search for country.
3. Extracts population and demographic information for the country selected.
4. Report is provided to business owner.

## EXTENSIONS

3. **Country does not exist**:
    1. Inform the business owner the country does not exist.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0