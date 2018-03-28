

### Search & Pagination Computer
#### General Assumption
* To be able to test all the functionalities related to search and pagination, we should have at least 3 pages, 21 computers.
* Assuming 25 computers for the cases below.

|ID | Precondition | Test Step | Expected Result| Priority|
|:-:| :----------: | :-------- | :------------- | :-----: |
| SPC-001 | CC-001 | Navigate to `http://computer-database.herokuapp.com/computers`. | Observe that the main page is displayed with; <ul><li>Page title</li><li>{total_number} of computers found</li><li>"Searchbox" and "Filter by name" button</li><li>"Add a new computer" button</li><li>Table of found computers with "10" results</li><li>Pagination buttons</li></ul> | P1 |
| SPC-002 | SPC-001 | Verify `Previous` button states. | <ul><li>Observe that `Previous` button is inactive on the first page.</li><li>Observe that `Previous` button is active on the second and until the last page.</li></ul> | P1 |
| SPC-003 | SPC-001 | Verify `Next` button states. | <ul><li>Observe that `Next` button is inactive on the last page.</li><li>Observe that `Next` button is active on the first and until the last page.</li></ul> | P1 |
| SPC-004 | SPC-001 | Tap on `Next` button. | <ul><li>Observe that user is redirected to the next page and next (10) computers on the second page are displayed successfully.</li><li>`Previous` button's state is become active.</li><li>Observe that "Displaying 11 to 20 of 25" is correctly shown.</li></ul>| P1 |
| SPC-005 | SPC-004 | Tap on `Next` button again. | <ul><li>Observe that user is redirected to the next (last) page and (5) computers on the last page are displayed successfully.</li><li>`Next` button's state is become inactive.</li><li>Observe that "Displaying 21 to 25 of 25" is correctly shown.</li></ul> | P1 |
| SPC-006 | SPC-005 | Tap on `Previous` button. | <ul><li>Observe that user is redirected to the previous page and (10) computers on the second page are displayed successfully.</li><li>`Next` button's state is become active.</li><li>Observe that "Displaying 11 to 20 of 25" is correctly shown.</li></ul> | P1 |
| SPC-007 | SPC-006 | Tap on `Previous` button again. | <ul><li>Observe that user is redirected to the previous (first) page and (10) computers on the first page are displayed successfully.</li><li>`Previous` button's state is become inactive.</li><li>Observe that "Displaying 1 to 10 of 25" is correctly shown.</li></ul> | P1 |
| SPC-008 | SPC-004, SPC-005, SPC-006, SPC-007 | Verify that page counter shows the correct information on every page. | Repeat the steps 4,5,6,7 and observe that counter always shows the correct counts. When there is enough data, counter switches between +10 & -10. | P1 |
| SPC-009 | SPC-004, SPC-005, SPC-006, SPC-007 | Check quick key presses on `Previous` and `Next` buttons, click on these buttons very quickly, one after another. | Observe that user cannot continue to navigate quickly between pages. | P3 |
| SPC-010 | SPC-001 | Check search functionality: <ul><li>Type introduced date, discontinued date and company name into the search field.</li><li>Type an exact computer name such as; "Amiga 1000".</li><li>Type lower-case, upper-case and mixed phrases such as; "APPLE, apple, APPle, LEN, len, LeN".</li></ul> | Observe that "Filter by computer name..." placeholder text is displayed inside the "Searchbox". After start typing, placeholder text is cleaned. <ul><li>Observe that filtering is made only with the computer names.</li><li>Observe that only "Amiga 1000" included results are displayed.</li><li>Observe that filtering is made only depending on the letters, lower-case and upper-case typing doesn't effect the search results for computer names. | P1 |
| SPC-011 | SPC-001 | Click on `Computer name` tab, twice. | Observe that `v` and `^` icons are displayed near the `Computer name` in order to show ascending/descending order. **Note: Currently, sorting function is not available but after clicking on `Computer name` tab, sorting should be changed between A-Z, 0-9 & Z-A, 9-0.**| P2 |
| SPC-012 | SPC-001 | Click on `Introduced` tab, twice. | Observe that `v` and `^` icons are displayed near the `Introduced` in order to show ascending/descending order. **Note: Currently, sorting function is not available but after clicking on `Introduced` tab, sorting should be changed between A-Z, 0-9 & Z-A, 9-0.**| P2 |
| SPC-013 | SPC-001 | Click on `Discontinued` tab, twice. | Observe that `v` and `^` icons are displayed near the `Discontinued` in order to show ascending/descending order. **Note: Currently, sorting function is not available but after clicking on `Discontinued` tab, sorting should be changed between A-Z, 0-9 & Z-A, 9-0.**| P2 |
| SPC-014 | SPC-001 | Click on `Company` tab, twice. | Observe that `v` and `^` icons are displayed near the `Company` in order to show ascending/descending order. **Note: Currently, sorting function is not available but after clicking on `Company` tab, sorting should be changed between A-Z, 0-9 & Z-A, 9-0.**| P2 |
| SPC-015 | SPC-010 | Check sorting after filtering results. | User should be able to sort the result by clicking on `Computer name`, `Introduced`, `Discontinued` and `Company` tabs. **Note: Currently, sorting function is not available but after clicking on these tabs, sorting should be changed between A-Z, 0-9 & Z-A, 9-0.**| P2 |
| SPC-016 | SPC-001 | Check `Filter by name` button functionality. <ul><li>Click on `Filter by name` button on the main page.</li><li>Click on `Filter by name` button on the page > 1.</li></ul> | <ul><li>Observe that all the computers (10) in the first page  are displayed successfully.</li><li>Observe that user is redirected to the first page if the page number != 1, and all the computers (10) in the first page, are displayed successfully.</li><li>`Previous` button's state is become inactive.</li><li>Observe that "Displaying 1 to 10 of 25" is correctly shown.</li></ul>| P2 |
| SPC-017 | SPC-001 | Check spelling errors. Check all the screens, texts and error messages. | Related content should be correct and is displayed without any spelling or grammatical errors.</li></ul> | P4 |
| SPC-018 | SPC-001 | Check user can search, filter and navigate through the pages in slower internet connetions (Wifi, Another Wifi, Edge, 3G - Hotspot). | User should be able to search, filter and navigate through the pages successfully in slower internet connections. | P4 |


### Input Field Controls for Search & Pagination Computer Flow

|ID | Precondition | Test Step | Expected Result| Priority|
|:-:| :----------: | :-------- | :------------- | :-----: |
| SPCI-001 | SPC-001 | Enter empty space into the searchbox and click on `Filter by name` button. | Observe that "Nothing to display" message is shown. | P3 |
| SPCI-002 | SPC-001 | Enter a very long text into the searchbox and click on `Filter by name` button. | Observe that user is redirected to http://computer-database.herokuapp.com/ computers?f={search_text} screen and only a page is shown. **Note: I entered a computer name with 8570 characters and navigated to the white page as above.| P3 |
| SPCI-003 | SPC-001 | Enter invalid characters into the searchbox and click on `Filter by name` button. | Observe that "Nothing to display" message is shown. | P3 |
| SPCI-004 | SPC-001 | <ul><li>Enter any valid computer name into the searchbox and click on `Filter by name` button.</li><li>Click on `Back` button on your browser.</li></ul> | <ul><li>Observe that related results are shown.</li><li>Observe that text in the searchbox is not cleared. This situaiton may misguide the user and it's not preferable user experience.</li></ul> | P2 |
| SPCI-005 | SPC-001 | <ul><li>Enter any computer name into the searchbox.</li><li>Click on `X` button inside the searchbox.</li></ul> | <ul><li>After typing the first character, `X` button appears.</li><li>Observe that text in the searchbox is deleted, list box is displayed showing latest searches.</li></ul> | P2 |
| SPCI-006 | SPC-001 | Check sorting functionality when the searchbox is filled.<ul><li>Enter any computer name into the searchbox.</li><li>Click on any tab title on the table.</li></ul> | <ul><li>Computer name is displayed.</li><li>Observe that sorting function is not available but text in the searchbox is also deleted automatically.</li></ul> | P2 |
| SPCI-007 | SPC-010 | Check caching in searchbox field. Enter several different computer names into the searchbox and click on `Filter by name` button.<ul><li>Check caching after several searches.</li><li>Check caching after cleaning browser's cache. Navigate to browser's settings and clear cache.</li></ul> | <ul><li>Click text field on the empty searchbox and observe that previously searched computer names are displayed as list, successfully.</li><li>Click text field on the empty searchbox and observe that previously searched computer names are not displayed.</li></ul> | P3 |


### Improvements for Search & Pagination Computer Flow

|ID | Precondition | Improvement |
|:-:| :----------: | :---------- |
| SPCIM-001 | SPC-009 | User should be able to click on `Previous` and `Next` buttons very quickly, one after another, in order to navigate between pages very quickly. Currently, it's not working, on MAC OSX 10.12.1 Google Chrome Version 56.0.2924.87 (64-bit).
| SPCIM-002 | SPC-011, SPC-012, SPC-013, SPC-014 | User should be able to use sorting function for all tabs. Sorting should be changed between A-Z, 0-9 & Z-A, 9-0 after clicking on the related tab title, such as; `Computer name`, `Introduced`, `Discontinued` and `Company` tabs.
| SPCIM-003 | N/A | All these features and screens should be tested on **responsive web**. Currently, it's not supported and causes UI bugs.
| SPCIM-004 | N/A | All these features and screens should be tested on different browsers, **cross-browser** testing should be performed, (Chrome, Firefox, Safari, IE, Edge, Opera). Currently, it's not supported as well.
| SPCIM-005 | N/A | All these features and screens should be tested on different Operating Systems, **cross-platform** testing should be performed. Currently, it's not supported and causes dislocations on table list.
| SPCIM-006 | N/A | All these features and screens should be tested on **mobile devices, tablets, etc.** Or, "Mobile Friendliness" test should be performed. Here are some results: <ul><li>**Page is not mobile friendly.**</li><li>Clickable elements too close together.</li><li>Viewport not set.</li><li>Text too small to read.</li></ul>
| SPCIM-007 | N/A | All these features and screens' speed should be tested in order to evaluate the performance. Here are some results: <ul><li>PageSpeed Score: (A-93%)</li><li>Fully Loaded Time: (524ms)</li><li>Total Page Size: (53.9KB)</li><li>Requests: (3)</li></ul>

**Note: Test cases above could've been grouped and structured logically when using any test management tool such as TestRail.** 

**Note: Bug reporting and improvement suggestions could've been performed in a much more structured way when using a tool such as Jira.**
