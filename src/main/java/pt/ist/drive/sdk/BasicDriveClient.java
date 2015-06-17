/**
 * Copyright © 2015 Instituto Superior Técnico
 *
 * This file is part of the Drive Client SDK.
 *
 * Drive Client SDK is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Drive Client SDK is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Drive Client SDK.  If not, see <http://www.gnu.org/licenses/>.
 */
package pt.ist.drive.sdk;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class BasicDriveClient extends DriveClient {

    private final String driveUrl;
    private final String tokenPath;
    private final String refreshToken;

    public BasicDriveClient(final String driveUrl, final String appId, final String appUser, final String refreshToken) {
        this.driveUrl = driveUrl;
        this.tokenPath = "/api/docs/oauth/" + appId + "/" + appUser;
        this.refreshToken = refreshToken;
    }

    @Override
    protected String accessToken() {
        final String s = target(tokenPath, refreshToken).get(String.class);
        final JsonObject o = new JsonParser().parse(s).getAsJsonObject();
        return o.get("access_token").getAsString();
    }

    @Override
    protected String driveUrl() {
        return driveUrl;
    }

}