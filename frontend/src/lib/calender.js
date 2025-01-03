import { useCalenderStore } from "@/stores/calender.js";
import { storeToRefs } from "pinia";

export function initializeCalendar() {
  (function ($) {
    "use strict";

    $(document).ready(function () {
      function c(passed_month, passed_year, calNum) {
        var calendar = calNum == 0 ? calendars.cal1 : calendars.cal2;
        makeWeek(calendar.weekline);
        calendar.datesBody.empty();
        var calMonthArray = makeMonthArray(passed_month, passed_year);
        var r = 0;
        var u = false;
        while (!u) {
          if (daysArray[r] == calMonthArray[0].weekday) {
            u = true;
          } else {
            calendar.datesBody.append('<div class="blank"></div>');
            r++;
          }
        }
        for (var cell = 0; cell < 42 - r; cell++) {
          if (cell >= calMonthArray.length) {
            calendar.datesBody.append('<div class="blank"></div>');
          } else {
            var shownDate = calMonthArray[cell].day;
            var iter_date = new Date(passed_year, passed_month, shownDate);

            if (
              ((shownDate != today.getDate() &&
                passed_month == today.getMonth()) ||
                passed_month != today.getMonth()) &&
              iter_date < today
            ) {
              var m = '<div class="past-date">';
            } else {
              var m = checkToday(iter_date) ? '<div class="today">' : "<div>";
            }
            calendar.datesBody.append(m + shownDate + "</div>");
          }
        }

        var koreanMonths = [
          "1월",
          "2월",
          "3월",
          "4월",
          "5월",
          "6월",
          "7월",
          "8월",
          "9월",
          "10월",
          "11월",
          "12월",
        ];
        calendar.calHeader
          .find("h2")
          .text(passed_year + "년" + " " + koreanMonths[passed_month]);

        // 날짜 클릭 이벤트 처리
        var clickedElement = calendar.datesBody.find("div"); // 여기서 clickedElement 정의
        clickedElement.on("click", function () {
          var clicked = $(this);
          if (clicked.hasClass("past-date")) {
            return;
          }

          // 기존 선택된 날짜 스타일 제거
          bothCals
            .find(".calendar_content")
            .find("div")
            .removeClass("selected");

          // 클릭된 날짜에 "selected" 클래스 추가
          clicked.addClass("selected");

          // 선택된 날짜 데이터 추출 및 저장
          var selectedDate = getClickedInfo(clicked, calendar);
          const scheduleStore = useCalenderStore();
          const store = storeToRefs(scheduleStore);
          store.selected.value = selectedDate; // 단일 날짜로 저장
        });
      }

      function getClickedInfo(element, calendar) {
        var clickedCalendar = calendar.name;
        var clickedMonth = clickedCalendar === "first" ? month : nextMonth;
        var clickedYear = clickedCalendar === "first" ? year : nextYear;
        return {
          date: parseInt(element.text()),
          month: clickedMonth,
          year: clickedYear,
        };
      }

      // 나머지 기존 코드 유지
      function makeMonthArray(passed_month, passed_year) {
        var e = [];
        for (
          var r = 1;
          r < getDaysInMonth(passed_year, passed_month) + 1;
          r++
        ) {
          e.push({
            day: r,
            weekday: daysArray[getWeekdayNum(passed_year, passed_month, r)],
          });
        }
        return e;
      }

      function makeWeek(week) {
        week.empty();
        for (var e = 0; e < 7; e++) {
          week.append("<div>" + daysArray[e].substring(0, 3) + "</div>");
        }
      }

      function getDaysInMonth(currentYear, currentMon) {
        return new Date(currentYear, currentMon + 1, 0).getDate();
      }

      function getWeekdayNum(e, t, n) {
        return new Date(e, t, n).getDay();
      }

      function checkToday(e) {
        var todayDate =
          today.getFullYear() +
          "/" +
          (today.getMonth() + 1) +
          "/" +
          today.getDate();
        var checkingDate =
          e.getFullYear() + "/" + (e.getMonth() + 1) + "/" + e.getDate();
        return todayDate === checkingDate;
      }

      function getAdjacentMonth(curr_month, curr_year, direction) {
        var theNextMonth;
        var theNextYear;
        if (direction === "next") {
          theNextMonth = (curr_month + 1) % 12;
          theNextYear = curr_month === 11 ? curr_year + 1 : curr_year;
        } else {
          theNextMonth = curr_month === 0 ? 11 : curr_month - 1;
          theNextYear = curr_month === 0 ? curr_year - 1 : curr_year;
        }
        return [theNextMonth, theNextYear];
      }

      function b() {
        today = new Date();
        year = today.getFullYear();
        month = today.getMonth();
        var nextDates = getAdjacentMonth(month, year, "next");
        nextMonth = nextDates[0];
        nextYear = nextDates[1];
      }

      var today, year, month, nextMonth, nextYear;
      var daysArray = [
        "Sunday",
        "Monday",
        "Tuesday",
        "Wednesday",
        "Thursday",
        "Friday",
        "Saturday",
      ];

      var cal1 = $("#calendar_first");
      var calHeader1 = cal1.find(".calendar_header");
      var weekline1 = cal1.find(".calendar_weekdays");
      var datesBody1 = cal1.find(".calendar_content");

      var cal2 = $("#calendar_second");
      var calHeader2 = cal2.find(".calendar_header");
      var weekline2 = cal2.find(".calendar_weekdays");
      var datesBody2 = cal2.find(".calendar_content");

      var bothCals = $(".calendar");

      var switchButton = bothCals
        .find(".calendar_header")
        .find(".switch-month");

      var calendars = {
        cal1: {
          name: "first",
          calHeader: calHeader1,
          weekline: weekline1,
          datesBody: datesBody1,
        },
        cal2: {
          name: "second",
          calHeader: calHeader2,
          weekline: weekline2,
          datesBody: datesBody2,
        },
      };

      b();
      c(month, year, 0);
      // c(nextMonth, nextYear, 1);

      switchButton.on("click", function () {
        var clicked = $(this);
        var generateCalendars = function (e) {
          var nextDatesFirst = getAdjacentMonth(month, year, e);
          var nextDatesSecond = getAdjacentMonth(nextMonth, nextYear, e);
          month = nextDatesFirst[0];
          year = nextDatesFirst[1];
          nextMonth = nextDatesSecond[0];
          nextYear = nextDatesSecond[1];

          c(month, year, 0);
          // c(nextMonth, nextYear, 1);
        };
        if (clicked.attr("class").indexOf("left") != -1) {
          generateCalendars("previous");
        } else {
          generateCalendars("next");
        }
      });
    });
  })(jQuery);
}
