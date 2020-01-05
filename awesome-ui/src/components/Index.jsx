import React, { Component } from "react";

import "./Index.css";

import { Link } from "react-router-dom";

class Index extends Component {
  constructor(props) {
    super(props);
    this.state = {};
  }
  render() {
    return (
      <div>
        <div className="container">
          <div className="row">
            <div className="col-md-5 scrambler-mode">
              <h2>Offline Scrambler Program</h2>
              <p>
                Generate scrambles by manually selecting events, number of
                rounds, competition name. You can use this to generate scrambles
                for training or for official competitions.
              </p>
              <p>
                <Link
                  to="webscrambles/offline"
                  className="btn btn-secondary"
                  role="button"
                >
                  Continue Offline »
                </Link>
              </p>
            </div>
            <div className="col-md-2"></div>
            <div className="col-md-5 scrambler-mode">
              <h2>Online Scrambler Program</h2>
              <p>
                This area is mostly for delegates and organisers. Syncronize
                with your WCA account to fetch data from your next competitions
                to keep you from manually input events, rounds, competition name
                and more.
              </p>
              <p>
                <Link
                  to="webscrambles/online"
                  className="btn btn-secondary"
                  role="button"
                >
                  Go Online »
                </Link>
              </p>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default Index;