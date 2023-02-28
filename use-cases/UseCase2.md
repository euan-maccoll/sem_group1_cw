# USE CASE: 2 Produce a Report on the popularity of certain languages of the world

## CHARACTERISTIC INFORMATION

### Goal in Context

As a Linguist I would like to know the popularity of certain languages of the world

### Scope

Company.

### Level

Primary task.

### Preconditions

We know the languages. Database contains popularity of certain languages.

### Success End Condition

A report is available for the linguist with the most popular languages in the world.

### Failed End Condition

No report is produced.

### Primary Actor

Linguist

### Trigger

A request for population information is made by the linguist.

## MAIN SUCCESS SCENARIO

1. Linguist requests popularity information for certain languages.
2. Search for language to get popularity information.
3. Extracts popularity information for the languages selected.
4. Report is provided to linguist.

## EXTENSIONS

3. **Language does not exist**:
    1. Inform the linguist the language does not exist.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0