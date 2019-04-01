
    function handleMouseMove(event) {
      var countryId = event.target.id;
      var tooltip = document.getElementById("tooltip");

      switch (countryId) {
        case "AE":
case "AF":
case "AL":
case "AM":
case "AO":
case "AR":
case "AT":
case "AU":
case "AZ":
case "BA":
case "BD":
case "BE":
case "BF":
case "BG":
case "BI":
case "BJ":
case "BN":
case "BO":
case "BR":
case "BS":
case "BT":
case "BW":
case "BY":
case "BZ":
case "CA":
case "CD":
case "CF":
case "CG":
case "CH":
case "CI":
case "CL":
case "CM":
case "CN":
case "CO":
case "CR":
case "CU":
case "CY":
case "CZ":
case "DE":
case "DJ":
case "DK":
case "DO":
case "DZ":
case "EC":
case "EE":
case "EG":
case "EH":
case "ER":
case "ES":
case "ET":
case "FK":
case "FI":
case "FJ":
case "FR":
case "GA":
case "GB":
case "GE":
case "GF":
case "GH":
case "GL":
case "GM":
case "GN":
case "GQ":
case "GR":
case "GT":
case "GW":
case "GY":
case "HN":
case "HR":
case "HT":
case "HU":
case "ID":
case "IE":
case "IL":
case "IN":
case "IQ":
case "IR":
case "IS":
case "IT":
case "JM":
case "JO":
case "JP":
case "KE":
case "KG":
case "KH":
case "KP":
case "KR":
case "XK":
case "KW":
case "KZ":
case "LA":
case "LB":
case "LK":
case "LR":
case "LS":
case "LT":
case "LU":
case "LV":
case "LY":
case "MA":
case "MD":
case "ME":
case "MG":
case "MK":
case "ML":
case "MM":
case "MN":
case "MR":
case "MW":
case "MX":
case "MY":
case "MZ":
case "NA":
case "NC":
case "NE":
case "NG":
case "NI":
case "NL":
case "NO"
:case "NP":
case "NZ":
case "OM":
case "PA":
case "PE":
case "PG":
case "PH":
case "PL":
case "PK":
case "PR":
case "PS":
case "PT":
case "PY":
case "QA":
case "RO":
case "RS":
case "RU":
case "RW":
case "SA":
case "SB":
case "SD":
case "SE":
case "SI":
case "SJ":
case "SK":
case "SL":
case "SN":
case "SO":
case "SR":
case "SS":
case "SV":
case "SY":
case "SZ":
case "TD":
case "TF":
case "TG":
case "TH":
case "TJ":
case "TL":
case "TM":
case "TN":
case "TR":
case "TT":
case "TW":
case "TZ":
case "UA":
case "UG":
case "US":
case "UY":
case "UZ":
case "VE":
case "VN":
case "VU":
case "YE":
case "ZA":
case "ZM":
          case "ZW":

        
          break;
        default:
          return tooltip.classList.remove("active");
      }

      var x = event.clientX;
      var y = event.clientY;

      tooltip.style.left = (x + 20) + "px";
      tooltip.style.top = (y - 20) + "px";
      tooltip.innerHTML = countryId;
      tooltip.classList.add("active");

    }