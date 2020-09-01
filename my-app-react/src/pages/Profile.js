import React, {Component} from 'react';

export default class Profile extends Component{
    render() {
        return <div>
            <table border="1">
                <tbody>
                    <tr>
                        <td>row 1, cell 1</td>
                        <td>row 1, cell 2</td>
                    </tr>
                    <tr>
                        <td>row 2, cell 1</td>
                        <td>row 2, cell 2</td>
                    </tr>
                </tbody>
            </table>
        </div>;
    }
}