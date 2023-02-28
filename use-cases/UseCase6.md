# USE CASE: 6 Produce a Report on the commonly spoken languages in a region

## CHARACTERISTIC INFORMATION

### Goal in Context

As a traveler, I want to be able to see the most commonly spoken languages in different parts of the world, so that I can better prepare for my trips and communicate with the locals.

### Scope

Company.

### Level

Primary task.

### Preconditions

We know the region. Database contains the region.

### Success End Condition

A report is available for the traveler containing the most commonly spoken languages in a region.

### Failed End Condition

No report is produced.

### Primary Actor

Traveler

### Trigger

A request for the commonly spoken languages in a region is made by the traveler.

## MAIN SUCCESS SCENARIO

1. Traveler requests commonly spoken languages in a region.
2. Select all countries in a region by code
3. Extract language information using country code.
4. Report is provided to traveler.

## EXTENSIONS

3. **Region does not exist**:
    1. Inform the traveler the region does not exist.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0