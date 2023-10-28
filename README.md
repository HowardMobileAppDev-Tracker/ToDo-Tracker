# Milestone 1 - To-Do Tracker (Unit 7)

## Table of Contents

1. [Overview](#Overview)
1. [Product Spec](#Product-Spec)
1. [Wireframes](#Wireframes)

## Overview

### Description

To-Do Tracker is a platform for users to keep track of tasks they want to execute with an associated deadline. It includes pages showing tasks to complete on each day, tasks that have been completed, tasks that are late, and a calendar view that gives access to tasks for each day.

### App Evaluation

- **Category:** Productivity
- **Mobile:** This app can provide a uniquely mobile experience by using features like real-time updates, notifications, and a calendar view to manage tasks.
- **Story:** The app’s story is compelling for anyone seeking task management and productivity enhancement. The value is clear to a broad audience, and friends or peers are likely to respond positively, as task management is a common need.
- **Market:** The market for this app is large, as task management is relevant to a wide range of people. It provides value to a broad audience.
- **Habit:** The habit-forming aspect depends on how well it keeps users engaged and reminds them of their tasks. If designed effectively, it can be habit-forming.
- **Scope:** The scope for this app appears well-formed and technically feasible. A stripped-down version with core task management features is still useful and interesting.

## Product Spec

### 1. User Features (Required and Optional)

**Required Features**

1. User can create a To-Do item.
2. User can set a reminder for To-Do items.
3. User can enable notifications for To-Do items.
4. User can mark a To-Do item as complete.


**Optional Features**

1. User can view completed To-Do items
2. User can view To-Do items by date by a date filter.
3. User can delete To-Do items.
4. User can add a deadline for To-Do items.

### 2. Screen Archetypes

- To-Do List Screen
  - User can see a list of items pending for a certain day. It shows items for "today" by default.
	- User can select whether to see all To-Do items, items marked as complete, or items not yet completed.
	- User can mark a To-Do item as complete.
	- User can launch the create To-Do screen.
  - User can delete a To-Do item.

- Calendar View Filter Screen
  - User can select a date to see To-Do items associated with it.

- Late To-Dos Screen
  - User can see a list of To-Do items that were not marked as complete past their deadline.
	- User can mark a To-Do item as complete.

- Create To-Do Screen
  - User can create a new To-Do item.
	- User can add a reminder to a To-Do item.
  - User can enable notification for a To-Do item.

### 3. Navigation

**Tab Navigation** (Tab to Screen)

* Pending To-Dos
* To-Dos by Day
* Late To-Dos

**Flow Navigation** (Screen to Screen)

- To-Do List Screen
  - => Create To-Do screen.

- Create To-Do screen
  - => To-Do List screen after saving a created To-Do item.

- Calendar View Filter Screen
  - => To-Do list screen after selecting what date to view. To-Do items for the date is shown on the To-Do screen.
 
- Late To-Dos Screen
  - => None.

## Wireframes

<img src="To-Do Tracker Wireframe.png">

<br>

<br>

<!-- ### [BONUS] Digital Wireframes & Mockups -->

<!-- ### [BONUS] Interactive Prototype -->
