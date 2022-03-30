---
layout: page title: User Guide
---

d'Intérieur is a **desktop app for interior designers to manage their contacts and projects, optimized for use via a 
Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). 
If you can type fast, d'Intérieur can get your contact management tasks done faster than traditional GUI apps.

If you utilize other online applications for interior designing and lack a centralized, robust, and efficient customer
management tool, this application will be able to meet your needs.

In this user guide, we will work you through the basic use cases and commands of the application, so that you can 
quickly get started and make full of use of what the application has to offer.

* Table of Contents {:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `dinterieur.jar` from [here](https://github.com/AY2122S2-CS2103T-T12-2/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your d'Intérieur.

4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app
   contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will
   open the help window.<br>
   Some example commands you can try:

    * **`list`** : Lists all contacts.

    * **`add n/Mary Jane p/12345678 e/maryJ@example.com`** : Adds a contact named Mary Jane to the Address Book.

    * **`delete`** `3` : Deletes the 3rd contact shown in the current list.

    * **`fav`** `2` : Adds the 2nd contact shown in the current list to your list of favourite contacts

    * **`favourites`** : Lists all your favourite contacts.

    * **`clear`** : Deletes all contacts.

    * **`exit`** : Exits the app.

6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/Mary Jane`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/Mary Jane t/friend` or as `n/Mary Jane`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of
  the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be
  ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help`

Shows a message explaining how to access the help page.

Format: `help`

![helpMessage](images/helpMessage.png)

### Adding a contact : `add`

You can add a contact to the address book with the address as an optional field.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL [a/ADDRESS] [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A contact can have 0 or 1 address
</div>
<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A contact can have any number of tags (including 0)
</div>

Examples:

* `add n/John Doe p/98765432 e/johnd@example.com`
* `add n/Mary Jane p/12345678 e/maryJ@example.com a/Bukit Timah t/completed`

### Listing all contacts : `list`

Shows a list of all contacts in the address book.

Format: `list`

### Editing a contact : `edit`

Edits an existing contact in the address book.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
To edit tags, use assign and unassign commands
</div>

* Edits the contact at the specified `INDEX`. The index refers to the index number shown in the displayed contact list.
  The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.

Examples:

* `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st contact to be `91234567`
  and `johndoe@example.com` respectively.
* `edit 1 n/John` Edits the name of the 1st contact to be `John`.

### Adding favourites : `fav`

Toggles the favourite status of your contacts. 
Favourited contacts **can be un-favourited** by running the same command on the contact again.

Format: `fav INDEX`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
You can run `fav INDEX` where `INDEX` is the index of a contact that currently belongs in your favourites list to remove them.
</div>

Examples: `fav 1` — Adds contact at index 1 to your list of favourites

![favourite](images/favourite_command.png)

- `fav 1` - Run the command for the same contact, and the favourite status will be toggled off.

![unfavourited](images/after_unfavourite_command.png)

### Listing Favourites : `favourites`

Lists all your favourite contacts to the list of displayed contacts.

Format: `favourites`

### Adding high importance flag : `impt`

Adds the contact to your list of contacts with high importance and a red flag will appear beside the contact's name to indicate that.

Format: `impt INDEX`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
When a red flag appears beside the contact's name, you can run `impt INDEX` again where `INDEX` is the index of a contact that currently belongs in your list of contacts with high importance to remove them.
</div>
<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
You may wish to use the `note` command to add a note to indicate why the contact is important. E.g. Mobility Issues.
</div>

### List contacts with high importance : `impts`

Shows you all contact(s) with high importance, tagged with the red flag.

Format: `impts`

### Prioritising relevant contacts to you : `sort`
Sort contacts by given criteria.

Format: `sort CRITERIA`
* `CRITERIA` should be written in lower-case.

Examples:
* `sort name` sorts list by name alphabetically.
* `sort fav` sorts list so that favourite contacts are on top of the list.

### Locating contacts by name : `find`

Find contacts whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Contacts matching at least one keyword will be returned (i.e. `OR` search). e.g. `Hans Bo` will return `Hans Gruber`
  , `Bo Yang`

Examples:

* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Locating contacts by tag : `findtag`

Finds contact with the given tag.

Format: `findtag KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `tag` will match `Tag`
* Only the tag is searched
* Only full words will be matched e.g. `Ta` will not match `Tag`
* List of contacts matching at least the searched tag\(s\) will be returned. e.g. `Tag1` will return `Contact` A with
  tags `Tag1` and `Tag2` will be returned.

Examples:

* `findtag Friends` returns contacts with tag `Friends`
* `findtag InProgress AlmostFinished` returns contacts tagged by at least both `InProgress` and `AlmostFinished`

### Creating a tag : `tag`

Creates a tag that can be assigned to any contact.

Format: `tag TAGNAME`

* A tag with the same `TAGNAME` can only be created once.
* The `TAGNAME` is case-insensitive. e.g. creating the tag `friends` will not allow `Friends` to be created. 

Examples:

* `tag Friends` creates a tag `Friends` to be stored in the address book.

### Assigning a tag to a contact : `assign`

Assigns a created tag to a contact.

Format: `assign INDEX TAGNAME`

* Assigns a `TAG` with a given `TAGNAME` to a contact at the specified `INDEX`
* The `TAG` given by the `TAGNAME` must be created first. 
* The `TAGNAME` is case-insensitive.
* The index refers to the index number shown in the displayed contact list.
* The index **must be a positive integer** 1, 2, 3, ...​
* The contact should have **at most one** `TAG` with a given unique `TAGNAME`.
* The contact assigned to the given `TAGNAME` cannot be assigned to the same `TAGNAME` again.
e.g. assigning the tag `friends` to Alice at index 1 will not allow `Friends` to be assigned to the same contact.

Examples:

* `assign 1 Friends` assigns a tag `Friends` to the contact at index `1`

### Unassigning a tag from a contact : `unassign`

Unassigns a created tag from a contact.

Format: `unassign INDEX TAGNAME`

* Removes a `TAG` with a given `TAGNAME` from a contact at the specified `INDEX`
* The `TAG` given by the `TAGNAME` must be created first.
* The `TAGNAME` is case-insensitive.
* The index refers to the index number shown in the displayed contact list.
* The index **must be a positive integer** 1, 2, 3, ...​
* The contact must have been assigned to this `TAG` previously.

Examples:

* `unassign 1 Friends` removes the tag `Friends` from the contact at index `1`

### Deleting a contact : `delete`

Deletes the specified contact from the address book.

Format: `delete INDEX`

* Deletes the contact at the specified `INDEX`.
* The index refers to the index number shown in the displayed contact list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:

* `list` followed by `delete 2` deletes the 2nd contact in the address book.
* `find Betsy` followed by `delete 1` deletes the 1st contact in the results of the `find` command.

### Adding deadlines to meet in relation to a contact : `deadline`

Creates a deadline that is placed under the profile of a contact.

Format: `deadline INDEX d/DESCRIPTION DATE [d/DESCRIPTION DATE]...`

- deadline must have description.
- The given date is added to the contact as deadline.
- Date should be dd/mm/yyyy

Example:

- `deadline 1 d/windows 01/01/2022` adds the description `windows` and date `01/01/2022` to the contact in index `1`.

List before `deadline` command:

![before 'deadline 1 d/windows 01/01/2022'](images/BeforeDeadlineCommand.png)

List after `deadline` command:

![after 'deadline 1 d/windows 01/01/2022'](images/AfterDeadlineCommand.jpg)

### Add additional notes to a contact : `note`

Adds the given note under the contact.

Format: `note INDEX r/NOTES`

- Notes are displayed in a list.
- The given note is appended to the existing list of notes at the end.

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
Notes store good-to-know information about the user. To classify contacts so that you can search for them, use tags instead.
</div>

Example:

`note 2 r/loves green` will create a note under the contact at index 2 that reads `loves green`

### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

d'Intérieur data are saved in the hard disk automatically after any command that changes the data. There is no need to
save manually.

### Editing the data file

d'Intérieur data are saved as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to
update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, d'Intérieur will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains
the data of your previous d'Intérieur home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action           | Format, Examples                                                                                                                                |
|------------------|-------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**          | `add n/NAME p/PHONE_NUMBER e/EMAIL [a/ADDRESS] [t/TAG]…​` <br> e.g., `add n/Mary Jane p/12345678 e/maryJ@example.com a/Bukit Timah t/completed` |
| **Assign Tag**   | `assign INDEX TAGNAME` <br> e.g., `assign 1 Friends`                                                                                            |
| **Clear**        | `clear`                                                                                                                                         |
| **Create Tag**   | `tag TAGNAME` <br> e.g., `tag Friends`                                                                                                          |
| **Deadline**     | `deadline INDEX DATE`<br> e.g., `deadline 1 01/01/2022`                                                                                         |
| **Delete**       | `delete INDEX`<br> e.g., `delete 3`                                                                                                             |
| **Edit**         | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] …​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`                            |
| **Fav**          | `fav INDEX` <br> e.g., `fav 1`                                                                                                                  |
| **Favourites**   | `favourites`                                                                                                                                    |
| **Find**         | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`                                                                                      |
| **Find Tag**     | `findtag KEYWORD [MORE_KEYWORDS}` <br> e.g., `findtag Friends`                                                                                  |
| **Help**         | `help`                                                                                                                                          |
| **Impt**         | `impt INDEX` <br> e.g., `impt 1`                                                                                                                |
| **Impts**        | `impts`                                                                                                                                         |
| **List**         | `list`                                                                                                                                          |
| **Note**         | `note INDEX r/NOTES`<br> e.g. `note 2 r/loves green`                                                                                            |
| **Unassign Tag** | `unassign INDEX TAGNAME` <br> e.g., `unassign 1 Friends`                                                                                        |
